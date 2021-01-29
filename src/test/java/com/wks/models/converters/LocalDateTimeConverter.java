package com.wks.models.converters;

import com.wks.Converter;
import com.wks.Character;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter implements Converter<LocalDateTime> {

    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

    @Override
    public String convert(LocalDateTime field, int maxLength) {
        if (field == null) return Character.whitespace.repeat(maxLength);
        return field.format(FORMATTER);
    }
}
