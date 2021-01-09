package com.wks.models;

import com.wks.BufferField;

import java.math.BigDecimal;

public class NumberTooLarge {
    @BufferField(order = 1, maxLength = 2)
    private final BigDecimal number;

    public NumberTooLarge(BigDecimal number) {
        this.number = number;
    }
}
