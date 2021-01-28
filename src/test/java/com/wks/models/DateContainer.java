package com.wks.models;

import com.wks.BufferField;
import com.wks.models.converters.LocalDateConverter;
import com.wks.models.converters.LocalDateTimeConverter;
import com.wks.models.converters.OffsetDateTimeConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class DateContainer {
    @BufferField(order = 1, maxLength = 8, converter = {LocalDateConverter.class})
    private final LocalDate localDate;
    @BufferField(order = 2, maxLength = 12, converter = {LocalDateTimeConverter.class})
    private final LocalDateTime localDateTime;
    @BufferField(order = 3, maxLength = 17, converter = {OffsetDateTimeConverter.class})
    private final OffsetDateTime offsetDateTime;

    public DateContainer(LocalDate localDate, LocalDateTime localDateTime, OffsetDateTime offsetDateTime) {
        this.localDate = localDate;
        this.localDateTime = localDateTime;
        this.offsetDateTime = offsetDateTime;
    }
}
