package com.wks;

import com.wks.models.NullStringContainer;
import com.wks.models.StringPadding;
import com.wks.models.TestOrderObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class WriteStringTests {

    @Test
    public void nullObjectReturnsNullString() {
        assertNull(new TuxedoBufferMapper().writeValueAsString(null));
    }

    @Test
    public void fieldsAppearInCorrectOrder() {
        // Given
        final TestOrderObject object = new TestOrderObject("AE", "AED");

        // When
        final String tuxedoMessage = new TuxedoBufferMapper().writeValueAsString(object);

        // Then
        assertEquals("AEDAE", tuxedoMessage);
    }

    @Test
    public void stringFieldsAreRightPaddedWithSpacesByDefault() {
        // Given
        StringPadding object = new StringPadding("TooShort");

        // When
        final String tuxedoMessage = new TuxedoBufferMapper().writeValueAsString(object);

        // Then
        assertEquals("TOOSHORT  ", tuxedoMessage);
    }

    @Test
    public void longStringFieldsAreClipped() {
        // Given
        StringPadding object = new StringPadding("TooooooLong");

        // When
        final String tuxedoMessage = new TuxedoBufferMapper().writeValueAsString(object);

        // Then
        assertEquals("TOOOOOOLON", tuxedoMessage);
    }

    @Test
    public void nullStringIsFilledWithSpaces() {
        // Given
        NullStringContainer object = new NullStringContainer();

        // When
        final String tuxedoMessage = new TuxedoBufferMapper().writeValueAsString(object);

        // Then
        assertEquals("          ", tuxedoMessage);
    }
}
