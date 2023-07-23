package com.wm.takehome.ui.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wm.takehome.domain.usecases.GetCountryListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListViewModel(
    savedStateHandle: SavedStateHandle? = null,
    private val getCountryListUseCase: GetCountryListUseCase
) : ViewModel() {

    private val _listState = MutableStateFlow(ListState())
    val listState: StateFlow<ListState> = _listState

    init {
        loadCountryList()
    }

    private fun loadCountryList() {
        viewModelScope.launch {
            _listState.update { it.copy(isLoading = true)}
            getCountryListUseCase.invoke()
                .onSuccess { list ->
                    _listState.update { it.copy(countries = list, isLoading = false) }
                }
                .onFailure {error ->
                    _listState.update { it.copy(errorValue = error, isLoading = false) }
                }
        }
    }
}