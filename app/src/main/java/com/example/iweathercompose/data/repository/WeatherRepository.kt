package com.example.iweathercompose.data.repository

import com.example.iweathercompose.data.dto.CurrentWeather
import com.example.iweathercompose.data.dto.ForecastWeather

interface WeatherRepository {
    suspend fun getCurrentWeather(url: String): CurrentWeather
    suspend fun getForecastWeather(url : String): ForecastWeather
}