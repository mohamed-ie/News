package com.example.news.domain.data.mapper

import com.example.news.domain.model.Country

interface CountryMapper {

    fun map(from:Any): Country
}