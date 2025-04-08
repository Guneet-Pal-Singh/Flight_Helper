package com.example.assignment2.Room

import androidx.room.*

@Dao
interface FlightDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFlight(flight: FlightEntity)

    @Query("SELECT * FROM flight_table ORDER BY id DESC")
    suspend fun getAllFlights(): List<FlightEntity>

    @Query("DELETE FROM flight_table")
    suspend fun deleteAll()
}
