package com.wks.models;

import com.wks.BufferField;

public class NonsequentialOrdersObject {
    @BufferField(order = 1, maxLength = 10)
    private final String firstName;
    @BufferField(order = 10, maxLength = 10)
    private final String lastName;

    public NonsequentialOrdersObject(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
