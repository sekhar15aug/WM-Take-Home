package com.wm.takehome.ui.list

import com.wm.takehome.domain.models.Country

data class ListState(
    val isLoading: Boolean = false,
    val countries: List<Country>? = null,
    val errorValue: Throwable? = null
) {
    val isError = errorValue != null
    val isEmpty = countries?.isEmpty() == true
}