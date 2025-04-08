package com.example.assignment2.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flight_table")
data class FlightEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val flightIata: String,
    val flightDate: String,
    val airlineName: String,
    val flightStatus: String,
    val latitude: Double?,
    val longitude: Double?,
    val altitude: Double?,
    val speed: Double?,
    val direction: Double?
)
