package com.example.iweathercompose.screens.viewmodel

import android.util.Log
import android.util.Log.e
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iweathercompose.data.dto.CurrentWeather
import com.example.iweathercompose.data.dto.ForecastWeather
import com.example.iweathercompose.data.repository.WeatherRepository
import com.example.iweathercompose.data.repository.WeatherRepositoryImpl
import com.example.iweathercompose.screens.Weather
import com.example.iweathercompose.screens.WeatherHomeUiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class WeatherHomeViewModel (): ViewModel(){
    private val weatherRepository: WeatherRepository = WeatherRepositoryImpl()
    var uiState : WeatherHomeUiState by mutableStateOf(WeatherHomeUiState.Loading)
    val exceptionHandler = CoroutineExceptionHandler { _ , _ ->
        uiState = WeatherHomeUiState.Error
    }

    private suspend fun getCurrentWeather() : CurrentWeather{
        return weatherRepository.getCurrentWeather()
    }
    private suspend fun getForecastWeather() : ForecastWeather{
        return weatherRepository.getForecastWeather()
    }

    fun getWeatherData(){
        viewModelScope.launch(exceptionHandler) {
            uiState = try {
                val currentWeather = async { getCurrentWeather() }.await()
                val forecastWeather = async { getForecastWeather() }.await()
                WeatherHomeUiState.Success(Weather(currentWeather,forecastWeather))
            }catch (e: Exception){
                WeatherHomeUiState.Error
            }
        }
    }
}