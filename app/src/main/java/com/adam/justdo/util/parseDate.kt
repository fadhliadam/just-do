package com.adam.justdo.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun parseDate(selectedDate: LocalDate?): String? {
    val dateFormat =
        if (selectedDate?.year == LocalDate.now().year) "E, MMM dd" else "E, MMM dd, yyyy"
    val formattedDate = when (selectedDate) {
        LocalDate.now() -> {
            "Today"
        }

        LocalDate.now().plusDays(1) -> {
            "Tomorrow"
        }

        else -> {
            selectedDate?.format(DateTimeFormatter.ofPattern(dateFormat))
        }
    }
    return formattedDate
}