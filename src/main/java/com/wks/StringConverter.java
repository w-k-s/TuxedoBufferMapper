package com.wks;

class StringConverter implements Converter<String> {

    @Override
    public String convert(String field, int maxLength) {
        if (field == null) return new String(new char[maxLength]).replace("\0", " ");
        if (field.length() >= maxLength) return field.substring(0, maxLength);
        return StringUtils.padRight(field, maxLength, ' ');
    }
}
