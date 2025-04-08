package com.example.assignment2_Q2.Data

data class Arrival(
    val airport: Airport,
    val baggageBelt: String,
    val checkInDesk: String,
    val gate: String,
    val predictedTime: PredictedTime,
    val quality: List<String>,
    val revisedTime: RevisedTime,
    val runway: String,
    val runwayTime: RunwayTime,
    val scheduledTime: ScheduledTime,
    val terminal: String
)