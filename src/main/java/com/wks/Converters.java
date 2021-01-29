package com.wks;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Converters {
    private Map<Class<?>, Class<? extends Converter>> value;

    private Converters(Builder builder) {
        this.value = Collections.unmodifiableMap(builder.value);
    }

    Map<Class<?>, Class<? extends Converter>> get() {
        return value;
    }

    public static Builder builder() {
        return new Builder();
    }

    static final class Builder {
        private Map<Class<?>, Class<? extends Converter>> value;

        private Builder() {
            value = new HashMap<>();
        }

        public Assignment assignType(Class<?> type) {
            return new Assignment(this, type);
        }

        public Converters build() {
            return new Converters(this);
        }
    }

    static final class Assignment {

        private final Builder builder;
        private final Class<?> type;

        private Assignment(Builder builder, Class<?> type) {
            this.builder = builder;
            this.type = type;
        }

        public Builder withConverter(Class<? extends Converter> converter) {
            builder.value.put(this.type, converter);
            return builder;
        }
    }
}
