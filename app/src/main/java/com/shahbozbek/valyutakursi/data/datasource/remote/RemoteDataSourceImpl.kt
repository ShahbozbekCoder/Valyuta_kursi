package com.shahbozbek.valyutakursi.data.datasource.remote

import com.shahbozbek.valyutakursi.data.api.ApiInterface
import com.shahbozbek.valyutakursi.data.model.CurrencyDto
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val api: ApiInterface
) : RemoteDataSource {
    override suspend fun getCurrencyData(): List<CurrencyDto> {
        val response = api.getCurrencyData()
        val body = response.body()
        return if (response.isSuccessful && body != null) {
            body
        } else {
            emptyList()
        }
    }
}