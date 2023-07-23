package com.wm.takehome.data.mapper

import com.wm.takehome.domain.mapper.Mapper
import com.wm.takehome.domain.models.Country
import com.wm.takehome.domain.models.CountryData

class CountryMapper : Mapper<CountryData, Country> {
    override fun map(countryData: CountryData) = Country(
        name = countryData.name,
        region = countryData.region,
        code = countryData.code,
        capital = countryData.capital
    )
}