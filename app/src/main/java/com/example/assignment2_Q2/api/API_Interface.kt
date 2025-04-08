package com.example.assignment2_Q2.api

import com.example.assignment2_Q2.Data.FlightResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API_Interface {

    @GET("flights/{searchBy}/{searchParam}")
    suspend fun getFlights(
        @Path("searchBy") searchBy: String,
        @Path("searchParam") searchParam: String,
        @Query("dateLocal") dateLocal: String? = null,
        @Query("dateLocalRole") dateLocalRole: String = "Both",
        @Query("withAircraftImage") withAircraftImage: Boolean = false,
        @Query("withLocation") withLocation: Boolean = false
    ): Response<FlightResponse>
}
