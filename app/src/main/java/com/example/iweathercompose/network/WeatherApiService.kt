package com.example.iweathercompose.network

import com.example.iweathercompose.data.dto.CurrentWeather
import com.example.iweathercompose.data.dto.ForecastWeather
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface WeatherApiService {
    @GET()
    suspend fun getCurrentWeather(@Url url: String): CurrentWeather

    @GET()
    suspend fun getForecastWeather(@Url url: String): ForecastWeather
}
