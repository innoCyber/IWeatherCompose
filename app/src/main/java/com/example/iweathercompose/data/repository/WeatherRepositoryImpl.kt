package com.example.iweathercompose.data.repository

import com.example.iweathercompose.data.dto.CurrentWeather
import com.example.iweathercompose.data.dto.ForecastWeather
import com.example.iweathercompose.network.WeatherApi

class WeatherRepositoryImpl: WeatherRepository {
    override suspend fun getCurrentWeather(): CurrentWeather {
        return WeatherApi.retrofitService.getCurrentWeather()
    }

    override suspend fun getForecastWeather(): ForecastWeather {
        return WeatherApi.retrofitService.getForecastWeather()
    }
}