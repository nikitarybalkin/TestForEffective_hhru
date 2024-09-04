package com.example.network.domain.model

import com.example.network.data.Offer
import com.example.network.data.Vocation
import com.example.network.data.Vocations

class VocationsModel (
    val offers: List<Offer>,
    val vacancies: List<Vocation>
)

fun Vocations.mapToDomain() = VocationsModel(
    offers,
    vacancies
)

fun VocationsModel.mapToData() = Vocations(
    offers,
    vacancies
)