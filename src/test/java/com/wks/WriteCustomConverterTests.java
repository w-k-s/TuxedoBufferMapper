package com.wks;

import com.wks.models.DateContainer;
import com.wks.models.StringPadding;
import com.wks.models.converters.HodorConverter;
import com.wks.models.converters.LocalDateConverter;
import com.wks.models.converters.LocalDateTimeConverter;
import com.wks.models.converters.OffsetDateTimeConverter;
import org.junit.Before;
import org.junit.Test;

import java.time.*;

import static org.junit.Assert.assertEquals;

public class WriteCustomConverterTests {

    private TuxedoBufferMapper mapper;

    @Before
    public void setup() {
        final Converters converters = Converters.builder()
                .assignType(LocalDate.class).withConverter(LocalDateConverter.class)
                .assignType(LocalDateTime.class).withConverter(LocalDateTimeConverter.class)
                .assignType(OffsetDateTime.class).withConverter(OffsetDateTimeConverter.class)
                .assignType(String.class).withConverter(HodorConverter.class)
                .build();

        mapper = new TuxedoBufferMapper(converters);
    }

    @Test
    public void testCustomConvertersOverrideInternalConverters() {
        // Given
        StringPadding object = new StringPadding("Groot");

        // When
        final String tuxedoMessage = mapper.writeValueAsString(object);

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
        final String tuxedoMessage = mapper.writeValueAsString(object);

        // Then
        assertEquals("20210101202101012210202101012210+0115", tuxedoMessage);
    }
}
