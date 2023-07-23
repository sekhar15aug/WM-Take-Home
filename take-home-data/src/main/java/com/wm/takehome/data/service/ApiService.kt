package com.wm.takehome.data.service

import com.wm.takehome.domain.models.CountryData
import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit api service
 */
interface ApiService {

    @GET("/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    suspend fun getCountries(): Response<List<CountryData>?>
}