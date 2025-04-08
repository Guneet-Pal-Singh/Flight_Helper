package com.example.assignment2_Q2.Data

data class Image(
    val author: String,
    val description: String,
    val htmlAttributions: List<String>,
    val license: String,
    val title: String,
    val url: String,
    val webUrl: String
)