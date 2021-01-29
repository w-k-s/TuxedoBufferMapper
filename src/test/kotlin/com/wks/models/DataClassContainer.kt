package com.wks.models

import com.wks.BufferField
import com.wks.models.converters.LocalDateConverter
import java.time.LocalDate

data class DataClassContainer(
    @field:BufferField(order = 1, maxLength = 20)
    private val firstName: String,
    @field:BufferField(order = 2, maxLength = 20)
    private val lastName: String,
    @field:BufferField(order = 3, maxLength = 8, converter = [LocalDateConverter::class])
    private val birthDate: LocalDate,
    @field:BufferField(order = 4, maxLength = 1)
    private val married: Boolean
)