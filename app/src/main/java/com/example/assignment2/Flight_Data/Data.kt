package com.example.assignment2.Flight_Data

data class Data(
    val aircraft: Any,
    val airline: Airline,
    val arrival: Arrival,
    val departure: Departure,
    val flight: Flight,
    val flight_date: String,
    val flight_status: String,
    val live: Live?
)