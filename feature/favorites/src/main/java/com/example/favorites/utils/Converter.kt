package com.example.favorites.utils

import com.example.database.domain.VacancyLocalModel
import com.example.network.data.Experience
import com.example.network.data.Salary
import com.example.network.data.Vocation

class Converter {
    fun convertFromLocalDataToModel(list: List<VacancyLocalModel>): List<Vocation> {
        var result: MutableList<Vocation> = mutableListOf()
        for (i in 0..list.lastIndex) {
            result.add(Vocation(
                address = list[i].vacancy.address,
                appliedNumber = list[i].vacancy.appliedNumber,
                company = list[i].vacancy.company,
                description = list[i].vacancy.description,
                experience = list[i].vacancy.experience,
                id = list[i].vacancy.id,
                isFavorite = list[i].vacancy.isFavorite,
                lookingNumber = list[i].vacancy.lookingNumber,
                publishedDate = list[i].vacancy.publishedDate,
                questions = list[i].vacancy.questions,
                responsibilities = list[i].vacancy.responsibilities,
                salary = list[i].vacancy.salary,
                schedules = list[i].vacancy.schedules,
                title = list[i].vacancy.title
                )

            )
        }
        return result
    }
}