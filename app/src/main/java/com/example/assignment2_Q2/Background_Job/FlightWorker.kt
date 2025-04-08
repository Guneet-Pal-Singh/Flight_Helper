package com.example.assignment2_Q2.Background_Job

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.assignment2_Q2.api.Repository

class FlightWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {

    private val repository = Repository(context)

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result {

        val dailyFlights = listOf("DL7188", "NZ4709", "WS1225","UA3449")

        for (flight in dailyFlights) {
            repository.fetchAndStoreDailyFlight(flight)
        }

        return Result.success()
    }
}
