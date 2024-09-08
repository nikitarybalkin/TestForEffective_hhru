package com.example.database.domain

import com.example.network.data.Vocation

class VacancyLocalModel (
    val id: Int,
    val vacancy: Vocation
)

fun VacanciesEntity.mapToDomain() = VacancyLocalModel(
    id,
    vacancy
)

fun VacancyLocalModel.mapToData() = VacanciesEntity(
    id,
    vacancy
)