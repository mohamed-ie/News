package com.example.news.domain.use_case.countries

import com.example.news.common.Constants
import com.example.news.di.DefaultDispatcher
import com.example.news.domain.repository.RestCountriesRepository
import com.example.news.domain.repository.SettingsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(
    private val restCountriesRepository: RestCountriesRepository,
) {

    operator fun invoke(countriesCodes: String = Constants.NEWS_SUPPORTED_COUNTRIES_CODES) =
        restCountriesRepository.getCountries(codes = countriesCodes)

}