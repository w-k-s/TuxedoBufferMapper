package com.wks;

class NumberConverter implements Converter<Number> {

    @Override
    public String convert(Number field, int maxLength) {
        if (field == null) return new String(new char[maxLength]).replace("\0", "0");
        final String numberString = field.toString();
        if (numberString.length() > maxLength) throw new NumberTooLargeException(numberString, maxLength);
        return StringUtils.padLeft(numberString, maxLength, '0');
    }
}
