package com.shahbozbek.valyutakursi.presentation.ui.main.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahbozbek.valyutakursi.core.Result
import com.shahbozbek.valyutakursi.domain.usecase.GetCurrencyDataUseCase
import com.shahbozbek.valyutakursi.presentation.ui.contracts.CurrencyUiEvent
import com.shahbozbek.valyutakursi.presentation.ui.contracts.CurrencyUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val getCurrencyDataUseCase: GetCurrencyDataUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CurrencyUiState(isLoading = true))
    val uiState: StateFlow<CurrencyUiState>
        get() = _uiState.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = CurrencyUiState(error = exception.message ?: "Unknown error")
    }

    init {
        load()
    }

    fun onEvent(event: CurrencyUiEvent) {
        when (event) {
            CurrencyUiEvent.Load -> load()
            CurrencyUiEvent.Retry -> TODO()
        }
    }

    fun load() {
        viewModelScope.launch(coroutineExceptionHandler) {
            getCurrencyDataUseCase.invoke()
                .onStart {
                    _uiState.update {
                        it.copy(
                            isLoading = true,
                            error = null
                        )
                    }
                }
                .catch {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = it.error ?: it.error ?: "Unknown error"
                        )
                    }
                }
                .collect { data ->
                    when (data) {
                        is Result.Success -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    currencies = data.value
                                )
                            }
                        }

                        is Result.Error -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    error = data.message
                                )
                            }
                        }

                        is Result.Loading -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = true
                                )
                            }
                        }
                    }
                }
        }
    }
}