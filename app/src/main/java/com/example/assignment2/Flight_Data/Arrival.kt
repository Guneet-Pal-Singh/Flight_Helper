package com.example.assignment2.Flight_Data

data class Arrival(
    val `actual`: Any,
    val actual_runway: Any,
    val airport: String,
    val baggage: String,
    val delay: Any,
    val estimated: Any,
    val estimated_runway: Any,
    val gate: String,
    val iata: String,
    val icao: String,
    val scheduled: String,
    val terminal: String,
    val timezone: String
)