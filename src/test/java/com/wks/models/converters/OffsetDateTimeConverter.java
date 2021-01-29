package com.wks.models.converters;

import com.wks.Converter;
import com.wks.Character;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class OffsetDateTimeConverter implements Converter<OffsetDateTime> {

    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmX");

    @Override
    public String convert(OffsetDateTime field, int maxLength) {
        if (field == null) return Character.whitespace.repeat(maxLength);
        return field.format(FORMATTER);
    }
}
