package com.wks;

import java.util.HashSet;
import java.util.Set;

class BooleanUtils {
    private final static Set<Class<?>> BOOLEAN_REFLECTED_PRIMITIVES;

    static {
        Set<Class<?>> s = new HashSet<>();
        s.add(boolean.class);
        BOOLEAN_REFLECTED_PRIMITIVES = s;
    }

    static boolean isBoolean(Class<?> type) {
        return Boolean.class.isAssignableFrom(type) || BOOLEAN_REFLECTED_PRIMITIVES.contains(type);
    }
}
