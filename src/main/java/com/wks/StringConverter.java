package com.wks;

class StringConverter implements Converter<String> {

    @Override
    public String convert(String field, int maxLength) {
        if (field == null) return Character.whitespace.repeat(maxLength);
        return StringUtils.padRight(field, maxLength, ' ');
    }
}
