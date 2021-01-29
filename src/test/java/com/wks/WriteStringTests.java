package com.wks;

import com.wks.exceptions.NonSequentialOrderException;
import com.wks.exceptions.NonUniqueOrderException;
import com.wks.models.*;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class WriteStringTests {

    @Test
    public void nullObjectReturnsNullString() {
        assertThat(new TuxedoBufferMapper().writeValueAsString(null)).isNull();
    }

    @Test
    public void fieldsAppearInCorrectOrder() {
        // Given
        final TestOrderObject object = new TestOrderObject("AE", "AED");

        // When
        final String tuxedoMessage = new TuxedoBufferMapper().writeValueAsString(object);

        // Then
        assertThat(tuxedoMessage).isEqualTo("AEDAE");
    }

    @Test
    public void stringFieldsAreRightPaddedWithSpacesByDefault() {
        // Given
        StringPadding object = new StringPadding("TooShort");

        // When
        final String tuxedoMessage = new TuxedoBufferMapper().writeValueAsString(object);

        // Then
        assertThat(tuxedoMessage).isEqualTo("TOOSHORT  ");
    }

    @Test
    public void longStringFieldsAreClipped() {
        // Given
        StringPadding object = new StringPadding("TooooooLong");

        // When
        final String tuxedoMessage = new TuxedoBufferMapper().writeValueAsString(object);

        // Then
        assertThat(tuxedoMessage).isEqualTo("TOOOOOOLON");
    }

    @Test
    public void nullStringIsFilledWithSpaces() {
        // Given
        NullStringContainer object = new NullStringContainer();

        // When
        final String tuxedoMessage = new TuxedoBufferMapper().writeValueAsString(object);

        // Then
        assertThat(tuxedoMessage).isEqualTo("          ");
    }

    @Test
    public void toStringConverterRespectsPadding() {
        // Given
        final ToStringConverterObject object = new ToStringConverterObject(ToStringConverterObject.Direction.WEST);

        // When
        final String tuxedoMessage = new TuxedoBufferMapper().writeValueAsString(object);

        assertThat(tuxedoMessage).isEqualTo("WEST ");
    }

    @Test
    public void multipleFieldsWithSameOrderThrowsException() {
        // Given
        MultipleFieldsWithSameOrderObject object = new MultipleFieldsWithSameOrderObject("Bob", "Bob");

        // When
        assertThatThrownBy(() -> new TuxedoBufferMapper().writeValueAsString(object))
                .isExactlyInstanceOf(NonUniqueOrderException.class)
                .hasMessage("Multiple fields can not have the same order. Duplicate order: 1, Field: lastName");
    }

    @Test
    public void nonsequentialOrdersThrowsException() {
        // Given
        NonsequentialOrdersObject object = new NonsequentialOrdersObject("Bob", "Bob");

        // When
        assertThatThrownBy(() -> new TuxedoBufferMapper().writeValueAsString(object))
                .isExactlyInstanceOf(NonSequentialOrderException.class)
                .hasMessage("Non-sequential orders: 1 and 10");
    }
}
