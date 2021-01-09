package com.wks;

public class NumberTooLargeException extends RuntimeException {
    public NumberTooLargeException(String numberString, int length) {
        super(String.format("Number %s is longer than %d characters", numberString, length));
    }
}
