package com.wks.models;

import com.wks.BufferField;

import java.math.BigDecimal;

public class NumberPadding {
    @BufferField(order = 1, maxLength = 4)
    private final int i;
    @BufferField(order = 2, maxLength = 8)
    private final long l;
    @BufferField(order = 3, maxLength = 4)
    private final float f;
    @BufferField(order = 4, maxLength = 8)
    private final double d;
    @BufferField(order = 5, maxLength = 1)
    private final byte b;
    @BufferField(order = 6, maxLength = 6)
    private final short s;
    @BufferField(order = 7, maxLength = 10)
    private final BigDecimal bd;

    public NumberPadding(int i, long l, float f, double d, byte b, short s, BigDecimal bd) {
        this.i = i;
        this.l = l;
        this.f = f;
        this.d = d;
        this.b = b;
        this.s = s;
        this.bd = bd;
    }

}
