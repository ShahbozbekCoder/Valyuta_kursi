package com.shahbozbek.valyutakursi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository {

    private val api: ApiInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://cbu.uz/uz/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(ApiInterface::class.java)
    }
    suspend fun getCurrencyData() : List<ConverterDataItem> {
        val response = api.getCurrencyData()
        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else {
            emptyList()
        }
    }


}