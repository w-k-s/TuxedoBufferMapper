package com.wks;

import com.wks.exceptions.NumberTooLargeException;
import com.wks.models.NumberPadding;
import com.wks.models.NumberTooLarge;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WriteNumberTests {

    @Test
    public void numbersAreLeftPaddedByZeroesByDefault() {
        // Given
        NumberPadding object = new NumberPadding(1, 2L, 3.0f, 4.0d, (byte) 5, (short) 6, BigDecimal.valueOf(7));

        // When
        String tuxedoMessage = new TuxedoBufferMapper().writeValueAsString(object);

        // Then
        assertThat(tuxedoMessage).isEqualTo("00010000000203.0000004.050000060000000007");
    }

    @Test
    public void numbersThatAreTooLargeThrowException() {
        // Given
        NumberTooLarge object = new NumberTooLarge(BigDecimal.valueOf(100L));

        assertThatThrownBy(() -> new TuxedoBufferMapper().writeValueAsString(object))
                .isInstanceOf(NumberTooLargeException.class)
                .hasMessage("Number 100 is longer than 2 characters");
    }

    @Test
    public void nullOrZeroNumbersAreFilledWithZeroes() {
        // Given
        NumberPadding object = new NumberPadding(0, 0L, 0.0f, 0.0d, (byte) 0, (short) 0, null);

        // When
        String tuxedoMessage = new TuxedoBufferMapper().writeValueAsString(object);

        // Then
        assertThat(tuxedoMessage).isEqualTo("00000000000000.0000000.000000000000000000");
    }

}
