package com.example.iweathercompose.data.repository

import com.example.iweathercompose.data.dto.CurrentWeather
import com.example.iweathercompose.data.dto.ForecastWeather
import com.example.iweathercompose.network.WeatherApiService
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val weatherApiService: WeatherApiService): WeatherRepository {
    override suspend fun getCurrentWeather(url : String): CurrentWeather {
        return weatherApiService.getCurrentWeather(url)
    }

    override suspend fun getForecastWeather(url : String): ForecastWeather {
        return weatherApiService .getForecastWeather(url)
    }
}