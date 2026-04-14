package com.shahbozbek.valyutakursi.data.api

import com.shahbozbek.valyutakursi.data.model.CurrencyDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("arkhiv-kursov-valyut/json/")
    suspend fun getCurrencyData(): Response<List<CurrencyDto>>
}