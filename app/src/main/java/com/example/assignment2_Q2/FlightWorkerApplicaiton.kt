package com.example.assignment2_Q2
import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.work.*
import com.example.assignment2_Q2.Background_Job.FlightWorker
import java.util.concurrent.TimeUnit

class FlightWorkerApplicaiton : Application() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        val workRequest = PeriodicWorkRequestBuilder<FlightWorker>(1, TimeUnit.DAYS).build()
        WorkManager.getInstance(applicationContext).enqueue(workRequest)
    }
}
