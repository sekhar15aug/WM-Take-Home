package com.wm.takehome.app

import com.wm.takehome.data.mapper.CountryMapper
import com.wm.takehome.data.repository.ListRepositoryImpl
import com.wm.takehome.data.service.RetrofitFactory
import com.wm.takehome.data.source.RemoteDataSource
import com.wm.takehome.domain.mapper.ListMapperImpl
import com.wm.takehome.domain.models.Country
import com.wm.takehome.domain.models.CountryData
import kotlinx.coroutines.Dispatchers

/**
 * Container of objects shared across the whole app
 * App container for manual DI
 */
class AppContainer {
    val listRepository = ListRepositoryImpl(
        RemoteDataSource(
            RetrofitFactory.createRetrofitService(),
            Dispatchers.IO
        ),
        ListMapperImpl(CountryMapper())
    )
}