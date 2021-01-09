package com.wks.models;

import com.wks.BufferField;

public class TestOrderObject {
    @BufferField(order = 2, maxLength = 2)
    private final String countryCode;
    @BufferField(order = 1, maxLength = 3)
    private final String currencyCode;

    public TestOrderObject(String countryCode, String currencyCode) {
        this.countryCode = countryCode;
        this.currencyCode = currencyCode;
    }
}
