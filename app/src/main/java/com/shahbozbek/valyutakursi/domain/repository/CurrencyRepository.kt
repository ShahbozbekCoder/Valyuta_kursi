package com.shahbozbek.valyutakursi.domain.repository

import com.shahbozbek.valyutakursi.core.Result
import com.shahbozbek.valyutakursi.domain.model.Currency
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    fun getCurrencyData(): Flow<Result<List<Currency>>>
}