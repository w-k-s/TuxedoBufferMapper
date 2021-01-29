package com.wks.models;

import com.wks.BufferField;
import com.wks.ToStringConverter;

public class ToStringConverterObject {
    public enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }

    @BufferField(order = 1, maxLength = 5, converter = {ToStringConverter.class})
    private final Direction direction;

    public ToStringConverterObject(Direction direction) {
        this.direction = direction;
    }
}
