package com.example.news.data.mapper

import com.example.news.domain.model.Country
import com.example.news.domain.data.mapper.CountryMapper
import javax.inject.Inject

class CountryMapperImpl @Inject constructor() : CountryMapper {

    override fun map(country: Any): Country {
        country as Map<String, Any>
        val nameMap: Map<String, Any> = country["name"] as Map<String, String>
        val nativeNameMap: Map<String, String> =
            (nameMap["nativeName"] as Map<String, Any>).values.first() as Map<String, String>

        val flag: String = country["flag"] as String
        val name: String = nameMap.getValue("common") as String
        val nativeName: String = nativeNameMap.getValue("common")
        val code: String = country["cca2"] as String

     return   Country(code, name = name, flag = flag, nativeName = nativeName)
    }

}