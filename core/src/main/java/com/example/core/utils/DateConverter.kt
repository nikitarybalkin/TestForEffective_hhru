package com.example.core.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Converters {
    fun formatDate(dateString: String): Pair<String, String> {
        // Задаем исходный формат даты
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        // Парсим строку в объект Date
        val date: Date = inputFormat.parse(dateString)

        // Формат для получения дня
        val dayFormat = SimpleDateFormat("d", Locale.getDefault())
        val day = dayFormat.format(date)

        // Формат для получения месяца
        val monthFormat = SimpleDateFormat("MMMM", Locale("ru"))
        val month = monthFormat.format(date)

        return Pair(day, month)
    }

    fun convertSchedules(list: List<String>): String {
        val result = list.mapIndexed { index, schedule ->
            if (index == 0) schedule.replaceFirstChar { it.uppercase() }
            else schedule
        }.joinToString(", ")

        return result
    }
}