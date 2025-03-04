package com.example.iweathercompose.data.repository

import com.example.iweathercompose.data.dto.CurrentWeather
import com.example.iweathercompose.data.dto.ForecastWeather

interface WeatherRepository {
    suspend fun getCurrentWeather(): CurrentWeather
    suspend fun getForecastWeather(): ForecastWeather
}