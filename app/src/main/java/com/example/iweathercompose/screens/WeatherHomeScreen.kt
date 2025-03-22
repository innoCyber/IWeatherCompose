package com.example.iweathercompose.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.iweathercompose.R
import com.example.iweathercompose.customUi.AppBackground
import com.example.iweathercompose.data.dto.CurrentWeather
import com.example.iweathercompose.utils.DEGREE
import com.example.iweathercompose.utils.PERCENTAGE
import com.example.iweathercompose.utils.formattedDate
import com.example.iweathercompose.utils.getIconUrl
import org.w3c.dom.Text
import java.nio.file.WatchEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherHomeScreen(uiState: WeatherHomeUiState, modifier: Modifier = Modifier) {

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        AppBackground(R.drawable.hsb)
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Weather App", style = MaterialTheme.typography.titleLarge) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        actionIconContentColor = Color.White,
                    )
                )
            },
            containerColor = Color.Transparent
        ) {
            Surface(
                color = Color.Transparent,
                modifier = modifier
                    .padding(it)
                    .fillMaxSize()
                    .wrapContentSize()
            ) {

                when(uiState){
                    is WeatherHomeUiState.Error ->   Text("Error")
                    is WeatherHomeUiState.Loading ->  Text("Loading....")
                    is WeatherHomeUiState.Success -> WeatherSection(uiState.weather)
                }
            }
        }
    }

}

@Composable
fun WeatherSection(weather: Weather, modifier: Modifier = Modifier) {

    Column (modifier.padding(8.dp)){
        CurrentWeatherSection(weather.currentWeather,modifier.weight(1f))
    }
    
}

@Composable
fun CurrentWeatherSection(currentWeather: CurrentWeather, modifier: Modifier = Modifier) {
    Column (modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("${currentWeather.name} ", style = MaterialTheme.typography.titleMedium)
        Text(formattedDate(currentWeather.dt, pattern = "MMM dd yyyy"), style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(20.dp))
        Text("${currentWeather.main?.temp}$DEGREE", style = MaterialTheme.typography.displayMedium)
        Spacer(modifier = Modifier.height(20.dp))
        Text("Feels like ${currentWeather.main?.feelsLike}$DEGREE", style = MaterialTheme.typography.titleMedium)

        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(getIconUrl(currentWeather.weather?.get(0)?.icon.toString()))
                    .crossfade(true)
                    .error(R.drawable.broken_image)
                    .build(),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )

            Text("${currentWeather.weather?.get(0)?.description}", style = MaterialTheme.typography.titleMedium)
        }

        Spacer(modifier = Modifier.height(20.dp))
        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically,modifier = Modifier.fillMaxWidth() ) {
            Column{
                Text("Humidity ${currentWeather.main?.humidity}$PERCENTAGE")
                Text("Pressure ${currentWeather.main?.pressure}hPa")
                Text("Visibility ${currentWeather.visibility}km")
            }
            Spacer(modifier = Modifier.width(10.dp))
            VerticalDivider(modifier = Modifier.height(100.dp).width(2.dp),thickness = 2.dp, color = Color.White)

            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text("Sunrise ${formattedDate(currentWeather.sys?.sunrise,"hh:mm a")}")
                Text("Sunset ${formattedDate(currentWeather.sys?.sunset,"hh:mm a")}")
            }
        }
    }
}

@Preview
@Composable
private fun WeatherHomeScreenPreview() {
   // WeatherHomeScreen( )
}