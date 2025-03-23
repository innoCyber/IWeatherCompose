package com.example.iweathercompose.screens

import com.example.iweathercompose.data.dto.CurrentWeather
import com.example.iweathercompose.data.dto.ForecastWeather

data class Weather(
    val currentWeather: CurrentWeather,
    val forecastWeather: ForecastWeather
)

sealed interface WeatherHomeUiState {
    data class Success(val weather: Weather): WeatherHomeUiState
    data object Error: WeatherHomeUiState
    data object Loading: WeatherHomeUiState
}

sealed interface NetworkConnectivityState{
    data object Available: NetworkConnectivityState
    data object UnAvailable: NetworkConnectivityState
}