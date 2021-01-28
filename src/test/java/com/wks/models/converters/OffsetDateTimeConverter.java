package com.wks.models.converters;

import com.wks.Converter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class OffsetDateTimeConverter implements Converter<OffsetDateTime> {

    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmX");

    @Override
    public String convert(OffsetDateTime field, int maxLength) {
        if (field == null) return new String(new char[maxLength]).replace("\0", " ");
        return field.format(FORMATTER);
    }
}
