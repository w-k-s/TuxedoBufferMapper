package com.wks.exceptions;

public class NonUniqueOrderException extends TuxedoBufferMapperException {
    public NonUniqueOrderException(int order, String fieldName) {
        super("Multiple fields can not have the same order. Duplicate order: %d, Field: %s", order, fieldName);
    }
}
