package com.wks.models;

import com.wks.BufferField;

public class StringPadding {
    @BufferField(order = 1, maxLength = 10)
    private final String name;

    public StringPadding(String name) {
        this.name = name;
    }
}
