package com.wks.exceptions;

import com.wks.TuxedoBufferMapper;

public class TuxedoBufferMapperException extends RuntimeException {
    public TuxedoBufferMapperException(String message) {
        super(message);
    }

    public TuxedoBufferMapperException(String message, Object... args) {
        super(String.format(message, args));
    }

    public TuxedoBufferMapperException(String message, Throwable cause) {
        super(message, cause);
    }
}
