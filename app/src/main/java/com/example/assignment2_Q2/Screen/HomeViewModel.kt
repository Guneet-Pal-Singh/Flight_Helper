package com.example.assignment2_Q2.Screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.assignment2_Q2.Data.FlightAverageStats
import com.example.assignment2_Q2.Data.FlightResponse
import com.example.assignment2_Q2.api.Repository
import com.example.assignment2_Q2.database.FlightEntity
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {

    private val flightResponse2 = MutableLiveData<FlightResponse?>()
    val flightResponse: LiveData<FlightResponse?> get() = flightResponse2

    val DB_Data: LiveData<List<FlightEntity>> = repository.fetchFromDB()

    val avgFlightStats: LiveData<List<FlightAverageStats>> = repository.getAverageFlightStats()

    private val _searchedFlightNumber = MutableLiveData<String>()
    val searchedFlightStats: LiveData<FlightAverageStats?> = _searchedFlightNumber.switchMap { flightNumber ->
        repository.getStatsForFlight(flightNumber)
    }

    fun getStatsForFlight(flightNumber: String) {
        _searchedFlightNumber.value = flightNumber
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchFlights(flightNumber: String) {
        viewModelScope.launch {
            try {
                val response = repository.getFlights(flightNumber)
                flightResponse2.value = response
            } catch (e: Exception) {
                flightResponse2.value = null
            }
        }
    }
}
