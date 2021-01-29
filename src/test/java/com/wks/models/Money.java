package com.wks.models;

import com.wks.BufferField;
import com.wks.ToStringConverter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {
    public enum CurrencyCode {
        USD(2, RoundingMode.HALF_UP),
        KWD(3, RoundingMode.HALF_UP);

        private final int scale;
        private final RoundingMode roundingMode;

        CurrencyCode(int scale, RoundingMode roundingMode) {
            this.scale = scale;
            this.roundingMode = roundingMode;
        }
    }

    @BufferField(order = 2, maxLength = 3, converter = ToStringConverter.class)
    private final CurrencyCode currency;
    @BufferField(order = 1, maxLength = 10)
    private final BigDecimal amount;

    public Money(CurrencyCode currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount.setScale(currency.scale, currency.roundingMode);
    }
}
