package com.example.assignment2_Q2.api

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import com.example.assignment2_Q2.Data.FlightAverageStats
import com.example.assignment2_Q2.Data.FlightResponse
import com.example.assignment2_Q2.database.FlightDao
import com.example.assignment2_Q2.database.FlightDatabase
import com.example.assignment2_Q2.database.FlightEntity
import java.time.Duration
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class Repository(context: Context) {

    private val flightDao = FlightDatabase.Companion.getDatabase(context).flightDao()

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getFlights(flightNumber: String): FlightResponse? {
        return try {
            Log.d("FlightRepository", "Fetching flights for $flightNumber")

            val response = API_Object.api.getFlights(
                searchBy = "number",
                searchParam = flightNumber,
                dateLocalRole = "Both",
                withAircraftImage = false,
                withLocation = true
            )

            if (response.isSuccessful) {
                val body = response.body()
                Log.d("FlightRepository", "Response received: $body")

                body?.firstOrNull()?.let {
                    val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
                    val arrivalScheduledStr = it.arrival?.scheduledTime?.local
                    val departureScheduledStr = it.departure?.scheduledTime?.local
                    val departureRevisedStr = it.departure?.revisedTime?.local?:departureScheduledStr
                    val predictedTimeStr=it.arrival?.predictedTime?.local
                    val arrivalRevisedStr=it.arrival?.revisedTime?.local?:predictedTimeStr

                    var delayMinutes = 0.0
                    var durationMinutes = 0.0

                    try {
                        if (!departureScheduledStr.isNullOrEmpty() && !departureRevisedStr.isNullOrEmpty()) {
                            val departureScheduled = OffsetDateTime.parse(departureScheduledStr.replace(" ", "T"), formatter)
                            val departureRevised = OffsetDateTime.parse(departureRevisedStr.replace(" ", "T"), formatter)
                            val arrivalRevised = OffsetDateTime.parse(arrivalRevisedStr?.replace(" ", "T"), formatter)

                            val delay = Duration.between(departureScheduled, departureRevised)
                            delayMinutes = delay.toMinutes().toDouble()
                            val duration=Duration.between(departureRevised,arrivalRevised)
                            durationMinutes = duration.toMinutes().toDouble()
                        }
                    } catch (e: Exception) {
                        Log.e("FlightRepository", "Error parsing time: ${e.localizedMessage}")
                    }

                    val flightEntity = FlightEntity(
                        flightName = it.airline?.name?.replace(" ", "") ?: "",
                        flightNumber = it.number ?: "",
                        predictedArrivalTime = predictedTimeStr ?: "",
                        arrivalScheduledTime = (arrivalScheduledStr ?: predictedTimeStr).toString(),
                        arrivalRevisedTime = (it.arrival?.revisedTime?.local ?: predictedTimeStr).toString(),
                        departureScheduledTime = departureScheduledStr ?: "",
                        departureRevisedTime = (departureRevisedStr ?: departureScheduledStr).toString(),
                        delay = delayMinutes,
                        duration = durationMinutes
                    )

                    flightDao.insertFlight(flightEntity)
                    Log.d("FlightRepository", "Flight inserted into Room DB with delay (minutes): $delayMinutes")
                }

                body
            } else {
                Log.e("FlightRepository", "Error code: ${response.code()}")
                null
            }
        } catch (e: Exception) {
            Log.e("FlightRepository", "Exception: ${e.localizedMessage}")
            null
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun fetchAndStoreDailyFlight(flightNumber: String) {
        getFlights(flightNumber)
    }

    fun fetchFromDB(): LiveData<List<FlightEntity>>{
        return flightDao.getAllFlights()
    }
    fun getAverageFlightStats(): LiveData<List<FlightAverageStats>> {
        return flightDao.getAverageFlightStats()
    }

    fun getStatsForFlight(flightNumber: String): LiveData<FlightAverageStats?> {
        return flightDao.getAverageStatsForFlight(flightNumber)
    }


}