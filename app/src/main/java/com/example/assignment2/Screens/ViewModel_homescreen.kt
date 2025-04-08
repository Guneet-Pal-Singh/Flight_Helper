package com.example.assignment2.Screens

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment2.Flight_Data.Flight_api_response
import com.example.assignment2.Repository
import kotlinx.coroutines.launch

class ViewModel_homescreen : ViewModel() {

    private val flightResponse2 = MutableLiveData<Flight_api_response?>()
    val flight_resp: LiveData<Flight_api_response?> = flightResponse2

    fun clearFlightResponse() {
        flightResponse2.value = null
    }

    fun fetchflightsinfo(flightNumber: String, context: Context) {
        viewModelScope.launch {
            Log.d("ViewModelHomeScreen", "Fetching flight details for $flightNumber")
            try {
                val response = Repository.getFlights(flightNumber, context)
                flightResponse2.value = response
                if (response != null) {
                    Log.d("ViewModelHomeScreen", "Received data: ${response.data}")
                } else {
                    Log.e("ViewModelHomeScreen", "Response is null")
                }
            } catch (e: Exception) {
                flightResponse2.value = null
                Log.e("ViewModelHomeScreen", "Error: ${e.localizedMessage}")
            }
        }
    }
}
