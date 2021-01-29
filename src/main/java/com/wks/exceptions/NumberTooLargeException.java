package com.wks.exceptions;

public class NumberTooLargeException extends TuxedoBufferMapperException {
    public NumberTooLargeException(String numberString, int length) {
        super("Number %s is longer than %d characters", numberString, length);
    }
}
