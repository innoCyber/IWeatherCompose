package com.example.iweathercompose

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.iweathercompose.screens.NetworkConnectivityState
import com.example.iweathercompose.screens.WeatherHomeScreen
import com.example.iweathercompose.screens.viewmodel.WeatherHomeViewModel
import com.example.iweathercompose.ui.theme.IWeatherComposeTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val client: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this)
        enableEdgeToEdge()
        setContent {
            WeatherApp(client)
        }
    }

    @Composable
    fun WeatherApp(client: FusedLocationProviderClient, modifier: Modifier = Modifier) {
        val weatherHomeViewModel: WeatherHomeViewModel = viewModel()
        val context = LocalContext.current
        var permissionGranted by remember { mutableStateOf(false) }
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { granted ->
            permissionGranted = granted
        }
        LaunchedEffect(Unit) {
            val isPermissionGranted = ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

            if (!isPermissionGranted) {
                launcher.launch(android.Manifest.permission.ACCESS_COARSE_LOCATION)
            } else {
                permissionGranted = true
            }
        }

        LaunchedEffect(permissionGranted) {
            if (permissionGranted) {
                client.lastLocation.addOnSuccessListener {
                    weatherHomeViewModel.setLocation(it.longitude, it.latitude)
                    weatherHomeViewModel.getWeatherData()
                }
            }
        }

        val networkConnectivityState by weatherHomeViewModel.networkConnectivityState.collectAsState()
        IWeatherComposeTheme {
            WeatherHomeScreen(
                isNetworkAvailable = networkConnectivityState == NetworkConnectivityState.Available,
                weatherHomeViewModel.uiState
            )
        }

    }

    @Preview
    @Composable
    private fun WeatherAppPrev() {
        IWeatherComposeTheme {
            //  WeatherHomeScreen()
        }
    }
}
