package com.wks;

import com.wks.exceptions.TuxedoBufferMapperException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TuxedoBufferMapper {

    private final static Converters DEFAULT_CONVERTERS = Converters.builder()
            .assignType(String.class).withConverter(StringConverter.class)
            .assignType(Number.class).withConverter(NumberConverter.class)
            .assignType(Boolean.class).withConverter(BooleanConverter.class)
            .build();

    private final Converters converters;

    public TuxedoBufferMapper() {
        this(DEFAULT_CONVERTERS);
    }

    public TuxedoBufferMapper(Converters customConverters) {
        this.converters = DEFAULT_CONVERTERS.appendOrOverwriteWith(customConverters);
    }

    public String writeValueAsString(Object object) {
        if (object == null) return null;

        final Class<?> clazz = object.getClass();
        final OrderValidator validator = OrderValidator.newSession();
        final Map<Integer, String> serializedFields = new HashMap<>();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(BufferField.class)) {
                final BufferField property = field.getAnnotation(BufferField.class);
                validator.validateOrderIsUnique(property.order(), getFieldName(field));
                if (property.converter().length > 0) {
                    String value = instantiateConverter(property.converter()[0]).convert(getFieldValue(field, object), property.maxLength());
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

        validator.validateOrdersAreSequential();

        return serializedFields.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .collect(Collectors.joining(""))
                .toUpperCase();
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
        return (Converter<T>) converters.get(clazz)
                .map(this::instantiateConverter)
                .orElseThrow(() -> new NullPointerException("Converter not registered: " + clazz));
    }

    private <T> Converter<T> instantiateConverter(Class<? extends Converter> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new TuxedoBufferMapperException("Exception while creating converter: " + clazz, e);
        }
    }

    private String trim(String field, int maxLength) {
        if (field.length() >= maxLength) return field.substring(0, maxLength);
        return field;
    }

    private String getFieldName(Field field) {
        return Optional.ofNullable(field)
                .map(Field::getName)
                .orElse("<No field Name>");
    }
}
