package com.wm.takehome.data.repository

import com.wm.takehome.data.source.RemoteDataSource
import com.wm.takehome.domain.mapper.ListMapper
import com.wm.takehome.domain.models.Country
import com.wm.takehome.domain.models.CountryData
import com.wm.takehome.domain.repositories.ListRepository

/**
 * Repository implementation for country list
 */
class ListRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val listMapper: ListMapper<CountryData, Country>
): ListRepository {

    // Cache of country list from service response
    private var countryList: List<Country> = emptyList()

    /**
     * Get countries using retrofit
     */
    override suspend fun getCountries(): Result<List<Country>?> {
        return if (countryList.isEmpty()) {
            try {
                val response = remoteDataSource.getCountryList()
                if (response.isSuccessful && response.body() != null) {
                    countryList = listMapper.map(response.body()!!)
                    Result.success(countryList)
                } else {
                    Result.failure(Throwable())
                }
            } catch (e: Exception) {
                Result.failure(Throwable())
            }
        } else {
            Result.success(countryList)
        }
    }
}