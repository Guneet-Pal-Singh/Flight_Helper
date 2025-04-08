package com.example.assignment2_Q2.Data

data class LocationXX(
    val altitude: Altitude,
    val groundSpeed: GroundSpeed,
    val lat: Double,
    val lon: Double,
    val pressure: Pressure,
    val pressureAltitude: PressureAltitude,
    val reportedAtUtc: String,
    val trueTrack: TrueTrack
)