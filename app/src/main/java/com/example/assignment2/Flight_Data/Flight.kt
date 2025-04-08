package com.example.assignment2.Flight_Data

data class Flight(
    val codeshared: Codeshared,
    val iata: String,
    val icao: String,
    val number: String
)