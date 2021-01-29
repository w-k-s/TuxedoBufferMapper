package com.wks;

public class ToStringConverter implements Converter<Object> {
    @Override
    public String convert(Object field, int maxLength) {
        if (field == null) return Character.whitespace.repeat(maxLength);
        return StringUtils.padRight(field.toString(), maxLength, ' ');
    }
}
