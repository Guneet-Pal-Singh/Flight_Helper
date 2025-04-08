package com.example.assignment2_Q2.Screen

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image // ✅ Correct import
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.assignment2_Q2.Data.FlightAverageStats
import com.example.assignment2_Q2.R // ✅ For R.drawable.flightTracker

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel) {
    var flightNumber by remember { mutableStateOf("") }
    val context = LocalContext.current
    val searchedStats by viewModel.searchedFlightStats.observeAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.flighttracker),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(250.dp)
                    .padding(bottom = 32.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                text = "Flight Tracker",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            OutlinedTextField(
                value = flightNumber,
                onValueChange = { flightNumber = it },
                label = { Text("Enter Flight Number") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    if (flightNumber.isBlank()) {
                        Toast.makeText(context, "Please enter a flight number", Toast.LENGTH_SHORT).show()
                    } else {
                        viewModel.getStatsForFlight(flightNumber)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Search Flight Stats")
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (searchedStats != null) {
                searchedStats?.let { stats ->
                    FlightStatsCard(stats)
                }
            } else if (flightNumber.isNotEmpty()) {
                Text("No data found for flight: $flightNumber", style = MaterialTheme.typography.bodyMedium)
            }
        }

        Button(
            onClick = {
                navController.navigate("averageDetails")
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            Text("View All Average Delays & Durations")
        }
    }
}

@Composable
fun FlightStatsCard(stats: FlightAverageStats) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Flight Number: ${stats.flightNumber}", style = MaterialTheme.typography.bodyLarge)
            Text("Average Delay: ${"%.2f".format(stats.avgDelay)} minutes")
            Text("Average Duration: ${"%.2f".format(stats.avgDuration)} minutes")
        }
    }
}
