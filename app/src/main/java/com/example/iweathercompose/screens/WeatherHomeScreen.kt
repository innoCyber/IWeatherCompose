package com.example.iweathercompose.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.iweathercompose.R
import com.example.iweathercompose.customUi.AppBackground
import org.w3c.dom.Text

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
                modifier = modifier.padding(it).fillMaxSize().wrapContentSize()
            ) {

                when(uiState){
                    is WeatherHomeUiState.Error ->  TODO()
                    is WeatherHomeUiState.Loading ->  TODO()
                    is WeatherHomeUiState.Success -> TODO()
                }
            }
        }
    }

}

@Preview
@Composable
private fun WeatherHomeScreenPreview() {
   // WeatherHomeScreen( )
}