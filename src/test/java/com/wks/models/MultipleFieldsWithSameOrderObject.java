package com.wks.models;

import com.wks.BufferField;

public class MultipleFieldsWithSameOrderObject {

    @BufferField(order = 1, maxLength = 1)
    private final String firstName;
    @BufferField(order = 1, maxLength = 1)
    private final String lastName;

    public MultipleFieldsWithSameOrderObject(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
