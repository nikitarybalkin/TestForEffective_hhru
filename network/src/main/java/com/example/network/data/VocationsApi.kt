package com.example.network.data

import retrofit2.http.GET

interface VocationsApi {
    @GET("/u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download ")
    suspend fun getVocations(): Vocations
}