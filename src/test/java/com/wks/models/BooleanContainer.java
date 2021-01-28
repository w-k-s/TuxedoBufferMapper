package com.wks.models;

import com.wks.BufferField;

public class BooleanContainer {

    @BufferField(order = 1, maxLength = 1)
    private final boolean isPrimitive;
    @BufferField(order = 2, maxLength = 1)
    private final Boolean isBoxed;

    public BooleanContainer(boolean isPrimitive, Boolean isBoxed) {
        this.isPrimitive = isPrimitive;
        this.isBoxed = isBoxed;
    }


}
