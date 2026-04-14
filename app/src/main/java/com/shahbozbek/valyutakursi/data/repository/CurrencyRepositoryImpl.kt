package com.shahbozbek.valyutakursi.data.repository

import com.shahbozbek.valyutakursi.core.Result
import com.shahbozbek.valyutakursi.data.datasource.remote.RemoteDataSource
import com.shahbozbek.valyutakursi.data.mapper.toDomain
import com.shahbozbek.valyutakursi.domain.model.Currency
import com.shahbozbek.valyutakursi.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : CurrencyRepository {
    override fun getCurrencyData(): Flow<Result<List<Currency>>> = flow {
        emit(Result.Loading)
        try {
            val dtoList = remoteDataSource.getCurrencyData()
            emit(Result.Success(dtoList.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(Result.Error(message = e.message))
        }
    }
}