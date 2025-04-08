package com.example.assignment2_Q2

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignment2_Q2.Screen.HomeViewModel
import com.example.assignment2_Q2.Screen.HomeScreen
import com.example.assignment2_Q2.Screen.AverageDetailsScreen
import com.example.assignment2_Q2.api.Repository
import com.example.assignment2_Q2.ui.theme.Assignment_2Theme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val repository = Repository(applicationContext)
        val viewModel = HomeViewModel(repository)

        setContent {
            Assignment_2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("home") { HomeScreen(navController,viewModel) }

                        composable("averageDetails") {AverageDetailsScreen(viewModel)}
                    }
                }
            }
        }
    }
}
