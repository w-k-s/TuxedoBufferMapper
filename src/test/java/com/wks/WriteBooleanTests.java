package com.wks;

import com.wks.models.BooleanContainer;
import com.wks.models.NullStringContainer;
import com.wks.models.StringPadding;
import com.wks.models.TestOrderObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class WriteBooleanTests {


    @Test
    public void nullStringIsFilledWithSpaces() {
        // Given
        BooleanContainer object = new BooleanContainer(false, null);

        // When
        final String tuxedoMessage = new TuxedoBufferMapper().writeValueAsString(object);

        // Then
        assertEquals("N ", tuxedoMessage);
    }

    @Test
    public void trueBooleansAreMarshalledIntoYByDefault() {
        // Given
        BooleanContainer object = new BooleanContainer(true, Boolean.TRUE);

        // When
        final String tuxedoMessage = new TuxedoBufferMapper().writeValueAsString(object);

        // Then
        assertEquals("YY", tuxedoMessage);
    }

    @Test
    public void falseBooleansAreMarshalledIntoNByDefault() {
        // Given
        BooleanContainer object = new BooleanContainer(false, Boolean.FALSE);

        // When
        final String tuxedoMessage = new TuxedoBufferMapper().writeValueAsString(object);

        // Then
        assertEquals("NN", tuxedoMessage);
    }
}
