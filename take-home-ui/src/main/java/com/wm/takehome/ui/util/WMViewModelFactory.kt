package com.wm.takehome.ui.util

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.wm.takehome.domain.repositories.ListRepository
import com.wm.takehome.domain.usecases.GetCountryListUseCase
import com.wm.takehome.ui.list.ListViewModel

@Suppress("UNCHECKED_CAST")
class WMViewModelFactory constructor(
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null,
    private val listRepository: ListRepository
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when {
            isAssignableFrom(ListViewModel::class.java) ->
                ListViewModel(
                    getCountryListUseCase = GetCountryListUseCase(listRepository = listRepository)
                )

            else -> IllegalArgumentException("Can't create ViewModel for ${modelClass.name}")
        }
    } as T
}