package com.example.iweathercompose.network

import com.example.iweathercompose.data.dto.CurrentWeather
import com.example.iweathercompose.data.dto.ForecastWeather
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface WeatherApiService {

   // @GET("weather?lat=2.00&lon=4.89&appid=80036686eb91b4cb4e58e75d4b1da146&units=imperial")
    @GET()
    suspend fun getCurrentWeather(@Url url : String): CurrentWeather

   // @GET("forecast?lat=2.00&lon=4.89&appid=80036686eb91b4cb4e58e75d4b1da146&units=imperial")
    @GET()
    suspend fun getForecastWeather(@Url url : String): ForecastWeather
}

object WeatherApi {
    val retrofitService: WeatherApiService =  retrofit.create(WeatherApiService::class.java)
}