package com.wks.models.converters;

import com.wks.Converter;
import com.wks.Character;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter implements Converter<LocalDate> {

    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Override
    public String convert(LocalDate field, int maxLength) {
        if (field == null) return Character.whitespace.repeat(maxLength);
        return field.format(FORMATTER);
    }
}
