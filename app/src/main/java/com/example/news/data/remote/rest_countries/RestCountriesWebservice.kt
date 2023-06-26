package com.example.news.data.remote.rest_countries

import retrofit2.http.GET
import retrofit2.http.Query

interface RestCountriesWebservice {
    companion object {
        const val BASE_URL = "https://restcountries.com/v3.1/"
    }

    //comma separated string  ==> "eg,us,fr"
    @GET("alpha")
    suspend fun getCountries(@Query("codes") string: String): Any


}