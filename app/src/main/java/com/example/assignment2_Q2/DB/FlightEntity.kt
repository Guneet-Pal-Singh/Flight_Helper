package com.example.assignment2_Q2.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.time.Duration

@Entity(tableName = "flights")
data class FlightEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val flightName: String,
    val flightNumber: String,
    val predictedArrivalTime: String,
    val arrivalScheduledTime: String,
    val arrivalRevisedTime: String,
    val departureScheduledTime: String,
    val departureRevisedTime: String,
    val delay: Double,
    val duration: Double
)
