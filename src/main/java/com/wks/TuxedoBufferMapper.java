package com.wks;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TuxedoBufferMapper {

    private final static Map<Class<?>, Class<? extends Converter>> DEFAULT_CONVERTERS;

    static {
        Map<Class<?>, Class<? extends Converter>> _converters = new HashMap<>();
        _converters.put(String.class, StringConverter.class);
        _converters.put(Number.class, NumberConverter.class);
        DEFAULT_CONVERTERS = Collections.unmodifiableMap(_converters);
    }

    private Map<Class<?>, Class<? extends Converter>> converters;

    public TuxedoBufferMapper() {
        this.converters = DEFAULT_CONVERTERS;
    }

    public String writeValueAsString(Object object) {
        if (object == null) return null;

        final Class<?> clazz = object.getClass();
        final Map<Integer, String> serializedFields = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(BufferField.class)) {
                final BufferField property = field.getAnnotation(BufferField.class);
                if (field.getType().equals(String.class)) {
                    String value = createConverter(String.class).convert(getFieldValue(field, object), property.maxLength());
                    serializedFields.put(property.order(), value);
                } else if (NumberUtils.isNumeric(field.getType())) {
                    final Number number = getFieldValue(field, object);
                    String value = createConverter(Number.class).convert(number, property.maxLength());
                    serializedFields.put(property.order(), value);
                }
            }
        }

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
        try {
            return converters.get(clazz).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
