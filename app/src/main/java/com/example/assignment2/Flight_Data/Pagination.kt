package com.example.assignment2.Flight_Data

data class Pagination(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val total: Int
)