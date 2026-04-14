package com.shahbozbek.valyutakursi.data.datasource.remote

import com.shahbozbek.valyutakursi.data.model.CurrencyDto

interface RemoteDataSource {
    suspend fun getCurrencyData() : List<CurrencyDto>
}