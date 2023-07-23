package com.wm.takehome.domain.repositories

import com.wm.takehome.domain.models.Country

/**
 * List repository
 */
interface ListRepository {
    suspend fun getCountries(): Result<List<Country>?>
}