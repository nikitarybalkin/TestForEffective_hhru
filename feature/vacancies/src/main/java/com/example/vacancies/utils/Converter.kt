package com.example.vacancies.utils

import com.example.database.domain.VacancyLocalModel
import com.example.network.data.Vocation
import kotlin.random.Random

class Converter {
    fun convertFromModelToLocalData(list: Vocation): VacancyLocalModel {
        return VacancyLocalModel(id = Random.nextInt(1, 100000), vacancy = list)
    }
}