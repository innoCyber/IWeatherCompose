package com.example.iweathercompose.data.repository

import com.example.iweathercompose.data.dto.CurrentWeather
import com.example.iweathercompose.data.dto.ForecastWeather
import com.example.iweathercompose.network.WeatherApi

class WeatherRepositoryImpl: WeatherRepository {
    override suspend fun getCurrentWeather(url : String): CurrentWeather {
        return WeatherApi.retrofitService.getCurrentWeather(url)
    }

    override suspend fun getForecastWeather(url : String): ForecastWeather {
        return WeatherApi.retrofitService.getForecastWeather(url)
    }
}