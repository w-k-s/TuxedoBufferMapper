package com.wks;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class Converters {
    private Map<Class<?>, Class<? extends Converter>> value;

    private Converters(Map<Class<?>, Class<? extends Converter>> value) {
        this.value = Collections.unmodifiableMap(value);
    }

    Converters appendOrOverwriteWith(Converters converters) {
        final Map<Class<?>, Class<? extends Converter>> combined = new HashMap<>(value);
        combined.putAll(converters.get());
        return new Converters(combined);
    }

    Map<Class<?>, Class<? extends Converter>> get() {
        return value;
    }

    Optional<Class<? extends Converter>> get(Class<?> clazz) {
        return Optional.ofNullable(this.value.get(clazz));
    }

    public static Builder builder() {
        return new Builder();
    }

    static final class Builder {
        private Map<Class<?>, Class<? extends Converter>> value;

        private Builder() {
            value = new HashMap<>();
        }

        public Link setConverter(Class<? extends Converter> converter) {
            return new Link(this, converter);
        }

        public Converters build() {
            return new Converters(this.value);
        }
    }

    static final class Link {

        private final Builder builder;
        private final Class<? extends Converter> converter;

        private Link(Builder builder, Class<? extends Converter> converter) {
            this.builder = builder;
            this.converter = converter;
        }

        public Builder forType(Class<?> type) {
            builder.value.put(type, this.converter);
            return builder;
        }
    }
}
