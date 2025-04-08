package com.example.assignment2_Q2.Data

data class FlightResponseItem(
    val aircraft: Aircraft,
    val airline: Airline,
    val arrival: Arrival,
    val callSign: String,
    val codeshareStatus: String,
    val departure: Departure,
    val greatCircleDistance: GreatCircleDistance,
    val isCargo: Boolean,
    val lastUpdatedUtc: String,
    val location: LocationXX,
    val number: String,
    val status: String
)