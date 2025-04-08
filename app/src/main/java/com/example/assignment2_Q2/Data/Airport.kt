package com.example.assignment2_Q2.Data

data class Airport(
    val countryCode: String,
    val iata: String,
    val icao: String,
    val localCode: String,
    val location: Location,
    val municipalityName: String,
    val name: String,
    val shortName: String,
    val timeZone: String
)