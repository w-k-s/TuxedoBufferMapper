package com.wks;

public interface Converter<T> {
    String convert(T field, int maxLength);
}
