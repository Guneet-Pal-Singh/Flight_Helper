package com.example.assignment2

import android.content.Context
import android.util.Log
import com.example.assignment2.Flight_Data.Flight_api_response
import com.example.assignment2.Room.FlightDatabase
import com.example.assignment2.Room.FlightEntity
import com.example.assignment2.api.API_Object

class Repository {
    companion object {
        private val api_service by lazy {
            API_Object.apiInterface
        }

        suspend fun getFlights(flight_number: String, context: Context): Flight_api_response? {
            return try {
                Log.d("API Result", "Fetching flights")
                val response = api_service.Flight_Info(flightIata = flight_number)
                if (response.isSuccessful) {
                    val flightResponse = response.body()
                    Log.d("API Result", "Response received: $flightResponse")

                    flightResponse?.data?.forEach { flight ->
                        val entity = FlightEntity(
                            flightIata = flight.flight.iata,
                            flightDate = flight.flight_date,
                            airlineName = flight.airline.name,
                            flightStatus = flight.flight_status,
                            latitude = flight.live?.latitude,
                            longitude = flight.live?.longitude,
                            altitude = flight.live?.altitude,
                            speed = flight.live?.speed_horizontal,
                            direction = flight.live?.direction
                        )

                        val db = FlightDatabase.getDatabase(context)
                        db.flightDao().insertFlight(entity)
                    }

                    flightResponse
                } else {
                    Log.e("API Result", "Error: ${response.code()}")
                    null
                }
            } catch (e: Exception) {
                Log.e("API Result", "Exception: ${e.localizedMessage}")
                null
            }
        }
    }
}
