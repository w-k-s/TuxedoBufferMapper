package com.wks.models.converters;

import com.wks.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HodorConverter implements Converter<String> {

    @Override
    public String convert(String field, int maxLength) {
        return "HODOR";
    }
}
