package com.wks;

class StringConverter implements Converter<String> {

    @Override
    public String convert(String field, int maxLength) {
        if (field == null) return new String(new char[maxLength]).replace("\0", " ");
        return StringUtils.padRight(field, maxLength, ' ');
    }
}
