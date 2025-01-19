package com.shahbozbek.valyutakursi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    private val repository = Repository()

    private val _currencyData = MutableLiveData<List<ConverterDataItem>>()
    val currencyData: LiveData<List<ConverterDataItem>>
        get() = _currencyData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    fun fetchData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val myData = repository.getCurrencyData()
                if (myData.isNotEmpty() && myData != null) {
                    _currencyData.value = myData
                    _error.value = null
                } else {
                    _error.value = "No data found"
                }
            } catch (e: Exception) {
                _error.value = "An error occurred: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}