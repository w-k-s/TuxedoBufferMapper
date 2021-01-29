package com.wks;

import com.wks.models.DateContainer;
import com.wks.models.StringPadding;
import com.wks.models.converters.HodorConverter;
import org.junit.Test;

import java.time.*;

import static org.junit.Assert.assertEquals;

public class WriteCustomConverterTests {

    @Test
    public void testCustomConvertersOverrideInternalConverters() {
        // Given
        StringPadding object = new StringPadding("Groot");

        // When
        final String tuxedoMessage = new TuxedoBufferMapper(Converters.builder()
                .assignType(String.class).withConverter(HodorConverter.class)
                .build()
        ).writeValueAsString(object);

        // Then
        assertEquals("HODOR", tuxedoMessage);
    }

    @Test
    public void testCustomConverters() {
        // Given
        final LocalDate localDate = LocalDate.of(2021, Month.JANUARY, 1);
        final LocalDateTime localDateTime = localDate.atTime(22,10);
        final OffsetDateTime offsetDateTime = localDateTime.atOffset(ZoneOffset.ofHoursMinutes(1,15));

        DateContainer object = new DateContainer(localDate, localDateTime, offsetDateTime);
        // When
        final String tuxedoMessage = new TuxedoBufferMapper().writeValueAsString(object);

        // Then
        assertEquals("20210101202101012210202101012210+0115", tuxedoMessage);
    }
}
