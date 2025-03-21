package com.kalyan.jetpackcomposepractice.data

import android.health.connect.datatypes.units.Temperature

data class TenDayWeatherForcastModel(
    val temperature: Int,
    val FeelsLike: Int,
    val condition: String,
    val chanceOfRain: Int,
    val date: String,
    val maxtemp_c: Int,
    val mintemp_c: Int
)
