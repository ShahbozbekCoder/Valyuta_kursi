package com.shahbozbek.valyutakursi

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("arkhiv-kursov-valyut/json/")
    suspend fun getCurrencyData(): Response<List<ConverterDataItem>>
}