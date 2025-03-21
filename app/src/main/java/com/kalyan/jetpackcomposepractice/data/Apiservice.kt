package com.kalyan.jetpackcomposepractice.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Apiservice {

    @GET("v1/forecast.json?key=47efc58244c54491aca15958241112")
    suspend fun getWeatherDataForecast(
        @Query("q") q: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String,
    ): Response<Weathermodel>
}