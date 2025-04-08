package com.example.assignment2_Q2.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.assignment2_Q2.Data.FlightAverageStats

@Dao
interface FlightDao {
    @Insert
    suspend fun insertFlight(flight: FlightEntity)

    @Query("SELECT * FROM flights")
    fun getAllFlights(): LiveData<List<FlightEntity>>

    @Query("""
        SELECT flightNumber, 
               AVG(delay) AS avgDelay, 
               AVG(duration) AS avgDuration 
        FROM flights 
        GROUP BY flightNumber
    """)
    fun getAverageFlightStats(): LiveData<List<FlightAverageStats>>

    @Query("""
    SELECT flightNumber, 
           AVG(delay) AS avgDelay, 
           AVG(duration) AS avgDuration 
    FROM flights 
    WHERE flightNumber = :flightNumber 
    GROUP BY flightNumber
""")
    fun getAverageStatsForFlight(flightNumber: String): LiveData<FlightAverageStats?>

}
