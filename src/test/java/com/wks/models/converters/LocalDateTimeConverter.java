package com.wks.models.converters;

import com.wks.Converter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter implements Converter<LocalDateTime> {

    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

    @Override
    public String convert(LocalDateTime field, int maxLength) {
        if (field == null) return new String(new char[maxLength]).replace("\0", " ");
        return field.format(FORMATTER);
    }
}
