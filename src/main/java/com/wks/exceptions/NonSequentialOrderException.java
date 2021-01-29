package com.wks.exceptions;

public class NonSequentialOrderException extends TuxedoBufferMapperException {
    public NonSequentialOrderException(int current, int next) {
        super("Non-sequential orders: %d and %d", current, next);
    }
}
