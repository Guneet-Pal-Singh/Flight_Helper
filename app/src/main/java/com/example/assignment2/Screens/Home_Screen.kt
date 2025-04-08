package com.example.assignment2.Screens

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import com.example.assignment2.R
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun Home_Screen(navController: NavHostController, viewModel: ViewModel_homescreen) {
    FlightTrackerHomeScreen(viewModel)
}

@Composable
fun FlightTrackerHomeScreen(viewModel: ViewModel_homescreen) {
    var flightNumber by remember { mutableStateOf("") }
    var isTracking by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var lastUpdated by remember { mutableStateOf("") }
    var shownLiveDataToast by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current
    val flightResponse by viewModel.flight_resp.observeAsState()

    LaunchedEffect(key1 = isTracking) {
        while (isTracking && flightNumber.isNotBlank()) {
            isLoading = true
            viewModel.fetchflightsinfo(flightNumber, context)
            lastUpdated = getCurrentTime()
            delay(120000)
        }
    }

    LaunchedEffect(flightResponse) {
        if (flightResponse != null) {
            isLoading = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = if (isTracking) 0.dp else 80.dp)
                .animateContentSize(),
            verticalArrangement = if (isTracking) Arrangement.Top else Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.flighttracker),
                contentDescription = "App Logo",
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text("Flight Tracker", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = flightNumber,
                onValueChange = {
                    flightNumber = it
                    errorMessage = null
                },
                label = { Text("Enter Flight Number") },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isTracking
            )

            if (errorMessage != null) {
                Text(
                    text = errorMessage!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    viewModel.clearFlightResponse()
                    isTracking = true
                    isLoading = true
                    lastUpdated = getCurrentTime()
                    shownLiveDataToast = false
                    errorMessage = null
                },
                enabled = flightNumber.isNotBlank() && !isTracking,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Start Tracking")
            }

            if (isTracking) {
                Button(
                    onClick = { isTracking = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Stop Tracking")
                }
            }

            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
            }

            if (lastUpdated.isNotBlank()) {
                Text("🔄 Last Updated: $lastUpdated")
            }
        }

        if (isTracking && flightResponse != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 300.dp)
                    .animateContentSize(),
                verticalArrangement = Arrangement.Top
            ) {
                flightResponse?.let { response ->
                    if (response.data.isEmpty()) {
                        if (errorMessage == null) {
                            errorMessage = "❌ Flight not found. Please check the number."
                        }
                        isTracking = false
                        isLoading = false
                        return@let
                    }

                    val liveFlights = response.data.filter { it.live != null }

                    if (liveFlights.isNotEmpty()) {
                        liveFlights.forEach { data ->
                            Spacer(modifier = Modifier.height(16.dp))
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Flight: ${data.flight.iata} (${data.airline.name})", style = MaterialTheme.typography.titleMedium)
                                    Text("Date: ${data.flight_date}")
                                    Text("Status: ${data.flight_status}")

                                    data.live?.let { live ->
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text("🛫 Live Info")
                                        Text("Location: ${live.latitude}, ${live.longitude}")
                                        Text("Altitude: ${live.altitude} m")
                                        Text("Speed: ${live.speed_horizontal} km/h")
                                        Text("Direction: ${live.direction}°")
                                        Text("Last Updated (Live): ${live.updated}")
                                    }
                                }
                            }
                        }
                    } else {
                        if (!shownLiveDataToast) {
                            Toast.makeText(context, "No live data available", Toast.LENGTH_SHORT).show()
                            shownLiveDataToast = true
                        }
                        isTracking = false
                    }
                }
            }
        }
    }
}

fun getCurrentTime(): String {
    val formatter = SimpleDateFormat("hh:mm:ss a", Locale.getDefault())
    return formatter.format(Date())
}
