package com.shahbozbek.valyutakursi.domain.usecase

import com.shahbozbek.valyutakursi.domain.repository.CurrencyRepository
import javax.inject.Inject

class GetCurrencyDataUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {
    suspend operator fun invoke() = repository.getCurrencyData()
}