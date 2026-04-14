package com.shahbozbek.valyutakursi.presentation.ui.contracts

import com.shahbozbek.valyutakursi.domain.model.Currency

data class CurrencyUiState(
    val isLoading: Boolean = true,
    val currencies: List<Currency> = emptyList(),
    val error: String? = null
)

sealed interface CurrencyUiEvent {
    data object Retry : CurrencyUiEvent
    data object Load : CurrencyUiEvent
}