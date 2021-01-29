package com.wks;

import com.wks.exceptions.NonSequentialOrderException;
import com.wks.exceptions.NonUniqueOrderException;
import com.wks.exceptions.TuxedoBufferMapperException;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class TuxedoBufferMapper {

    private final static Map<Class<?>, Class<? extends Converter>> DEFAULT_CONVERTERS;

    static {
        Map<Class<?>, Class<? extends Converter>> _converters = new HashMap<>();
        _converters.put(String.class, StringConverter.class);
        _converters.put(Number.class, NumberConverter.class);
        _converters.put(Boolean.class, BooleanConverter.class);
        DEFAULT_CONVERTERS = Collections.unmodifiableMap(_converters);
    }

    private Map<Class<?>, Class<? extends Converter>> converters;
    private Set<Integer> usedOrders = new TreeSet<>();

    public TuxedoBufferMapper() {
        this.converters = DEFAULT_CONVERTERS;
    }

    public TuxedoBufferMapper(Converters customConverters) {
        final Map<Class<?>, Class<? extends Converter>> allConverters = new HashMap<>();
        allConverters.putAll(DEFAULT_CONVERTERS); // allow default converters to be replaced
        allConverters.putAll(customConverters.get());
        this.converters = Collections.unmodifiableMap(allConverters);
    }

    public String writeValueAsString(Object object) {
        if (object == null) return null;

        final Class<?> clazz = object.getClass();
        final Map<Integer, String> serializedFields = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(BufferField.class)) {
                final BufferField property = field.getAnnotation(BufferField.class);
                validateOrdersAreUnique(property.order(), getFieldName(field));
                if (property.converter().length > 0) {
                    String value = createConverter(field.getType()).convert(getFieldValue(field, object), property.maxLength());
                    serializedFields.put(property.order(), trim(value, property.maxLength()));
                } else if (field.getType().equals(String.class)) {
                    String value = createConverter(String.class).convert(getFieldValue(field, object), property.maxLength());
                    serializedFields.put(property.order(), trim(value, property.maxLength()));
                } else if (NumberUtils.isNumeric(field.getType())) {
                    final Number number = getFieldValue(field, object);
                    String value = createConverter(Number.class).convert(number, property.maxLength());
                    serializedFields.put(property.order(), trim(value, property.maxLength()));
                } else if (BooleanUtils.isBoolean(field.getType())) {
                    final Boolean bool = getFieldValue(field, object);
                    String value = createConverter(Boolean.class).convert(bool, property.maxLength());
                    serializedFields.put(property.order(), trim(value, property.maxLength()));
                }
            }
        }

        validateOrdersAreSequential();

        return serializedFields.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .map(String::toUpperCase)
                .collect(Collectors.joining(""));
    }

    private <T> T getFieldValue(Field field, Object object) {
        return getFieldValueOrDefault(field, object, null);
    }

    private <T> T getFieldValueOrDefault(Field field, Object object, T defaultValue) {
        try {
            return (T) field.get(object);
        } catch (IllegalAccessException e) {
            return defaultValue;
        }
    }

    private <T> Converter<T> createConverter(Class<T> clazz) {
        return Optional.ofNullable(converters.get(clazz))
                .map(converterClazz -> {
                    try {
                        return converterClazz.newInstance();
                    } catch (InstantiationException | IllegalAccessException e) {
                        throw new TuxedoBufferMapperException("Exception while creating converter for class " + clazz, e);
                    }
                })
                .orElseThrow(() -> new NullPointerException("Converter not registered: " + clazz));
    }

    private String trim(String field, int maxLength) {
        if (field.length() >= maxLength) return field.substring(0, maxLength);
        return field;
    }

    private void validateOrdersAreUnique(int order, String fieldName) {
        if (!usedOrders.add(order)) {
            throw new NonUniqueOrderException(order, fieldName);
        }
    }

    private void validateOrdersAreSequential() {
        if (this.usedOrders.isEmpty()) return;

        List<Integer> usedOrders = new ArrayList<>(this.usedOrders);
        for (int i = 0; i + 1 < usedOrders.size(); i++) {
            int current = usedOrders.get(i);
            int next = usedOrders.get(i + 1);
            if (Math.abs(current - next) > 1) {
                throw new NonSequentialOrderException(current, next);
            }
        }
    }

    private String getFieldName(Field field) {
        return Optional.ofNullable(field)
                .map(Field::getName)
                .orElse("<No field Name>");
    }
}
