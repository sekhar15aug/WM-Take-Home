package com.wm.takehome.data.source

import com.wm.takehome.data.service.ApiService
import com.wm.takehome.domain.models.Country
import com.wm.takehome.domain.models.CountryData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response

class RemoteDataSource(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun getCountryList(): Response<List<CountryData>?> =
        withContext(ioDispatcher) {
            apiService.getCountries()
        }
}