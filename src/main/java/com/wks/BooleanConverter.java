package com.wks;

class BooleanConverter implements Converter<Boolean> {

    @Override
    public String convert(Boolean field, int maxLength) {
        if (field == null) return " ";
        return Boolean.TRUE.equals(field) ? "Y" : "N";
    }
}
