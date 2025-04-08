package com.example.assignment2.Flight_Data

data class Live(
    val altitude: Double,
    val direction: Double,
    val is_ground: Boolean,
    val latitude: Double,
    val longitude: Double,
    val speed_horizontal: Double,
    val speed_vertical: Double,
    val updated: String
)