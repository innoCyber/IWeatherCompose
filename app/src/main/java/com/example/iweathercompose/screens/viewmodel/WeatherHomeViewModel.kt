package com.example.iweathercompose.screens.viewmodel

import android.app.Application
import android.net.ConnectivityManager
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.iweathercompose.data.dto.CurrentWeather
import com.example.iweathercompose.data.dto.ForecastWeather
import com.example.iweathercompose.data.repository.DefaultNetworkConnectivityRepository
import com.example.iweathercompose.data.repository.NetworkConnectivityRepository
import com.example.iweathercompose.data.repository.WeatherRepository
import com.example.iweathercompose.data.repository.WeatherRepositoryImpl
import com.example.iweathercompose.screens.NetworkConnectivityState
import com.example.iweathercompose.screens.Weather
import com.example.iweathercompose.screens.WeatherHomeUiState
import com.example.iweathercompose.utils.getCurrentWeatherUrl
import com.example.iweathercompose.utils.getForecastWeatherUrl
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class WeatherHomeViewModel (private val networkConnectivityRepository: NetworkConnectivityRepository): ViewModel(){
    private val weatherRepository: WeatherRepository = WeatherRepositoryImpl()
    var uiState : WeatherHomeUiState by mutableStateOf(WeatherHomeUiState.Loading)
    private var longitude : Number = 0.0
    private var latitude : Number = 0.0
    val networkConnectivityState: StateFlow<NetworkConnectivityState> = networkConnectivityRepository.networkConnectivityState
    val exceptionHandler = CoroutineExceptionHandler { _ , _ ->
        uiState = WeatherHomeUiState.Error
    }

    private suspend fun getCurrentWeather() : CurrentWeather{
        return weatherRepository.getCurrentWeather(getCurrentWeatherUrl(latitude,longitude))
    }
    private suspend fun getForecastWeather() : ForecastWeather{
        return weatherRepository.getForecastWeather(getForecastWeatherUrl(latitude,longitude ))
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

    fun setLocation(long: Number, lat: Number){
        viewModelScope.launch {
            longitude = long
            latitude = lat
        }
    }

    companion object{
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as Application)
                val connectivityManager = application.getSystemService(ConnectivityManager::class.java)
                WeatherHomeViewModel(
                    networkConnectivityRepository = DefaultNetworkConnectivityRepository(connectivityManager)
                )
            }
        }
    }
}