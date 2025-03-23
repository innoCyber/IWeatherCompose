package com.example.iweathercompose.utils

const val API_KEY = "80036686eb91b4cb4e58e75d4b1da146"
const val DEGREE = "\u00b0 "
const val PERCENTAGE = "\u0025"

fun getCurrentWeatherUrl(latitude: Number, longitude: Number): String {
    return "weather?lat=$latitude&lon=$longitude&appid=80036686eb91b4cb4e58e75d4b1da146&units=imperial"
}
fun getForecastWeatherUrl(latitude: Number, longitude: Number): String {
    return "forecast?lat=$latitude&lon=$longitude&appid=80036686eb91b4cb4e58e75d4b1da146&units=imperial"
}
