package com.wks.models.converters;

import com.wks.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter implements Converter<LocalDate> {

    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Override
    public String convert(LocalDate field, int maxLength) {
        if (field == null) return new String(new char[maxLength]).replace("\0", " ");
        return field.format(FORMATTER);
    }
}
