package com.wks

import com.wks.models.DataClassContainer
import com.wks.models.converters.LocalDateConverter
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import java.time.Month

class WriteDataClassTests {

    @Test
    fun annotationsOnDataClassConstructorAreProcessed() {
        // Given
        val container = DataClassContainer(
            "John",
            "Example",
            LocalDate.of(2020, Month.JANUARY,1),
            true
        )

        // When
        val result = TuxedoBufferMapper().writeValueAsString(container)

        // Then
        assertThat(result).isEqualTo("JOHN                EXAMPLE             20200101Y")
    }
}