package com.wm.takehome.domain.usecases

import com.wm.takehome.domain.models.Country
import com.wm.takehome.domain.repositories.ListRepository

/**
 * Use case for country list
 */
class GetCountryListUseCase constructor(
    private val listRepository: ListRepository
) {
    suspend operator fun invoke(): Result<List<Country>?> =
        listRepository.getCountries()
}