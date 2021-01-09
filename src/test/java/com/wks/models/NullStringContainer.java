package com.wks.models;

import com.wks.BufferField;

public class NullStringContainer {
    @BufferField(order = 1, maxLength = 10)
    private final String nothing = null;
}
