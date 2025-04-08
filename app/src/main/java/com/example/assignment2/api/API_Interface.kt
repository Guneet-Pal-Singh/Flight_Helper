package com.example.assignment2.api
import com.example.assignment2.Flight_Data.Flight_api_response
import com.example.assignment2.constant.Constants
//import com.example.assignment2.Flight_Data.data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface API_Interface {
    @GET("flights")
    suspend fun Flight_Info(
        @Query("access_key") apiKey: String = Constants.API_KEY,
        @Query("callback") callback: String?= null,
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0,
        @Query("flight_status") flightStatus: String? = null,
        @Query("flight_iata") flightIata: String? = null,
        @Query("flight_icao") flightIcao: String? = null,
        @Query("flight_number") flightNumber: String? = null,
        @Query("flight_date") flightDate: String? = null,
        @Query("dep_iata") depIata: String? = null,
        @Query("arr_iata") arrIata: String? = null,
        @Query("dep_icao") depIcao: String? = null,
        @Query("arr_icao") arrIcao: String? = null,
        @Query("airline_name") airlineName: String? = null,
        @Query("airline_iata") airlineIata: String? = null,
        @Query("airline_icao") airlineIcao: String? = null,
        @Query("min_delay_dep") minDelayDep: Int? = null,
        @Query("min_delay_arr") minDelayArr: Int? = null,
        @Query("max_delay_dep") maxDelayDep: Int? = null,
        @Query("max_delay_arr") maxDelayArr: Int? = null,
        @Query("arr_scheduled_time_arr") arrScheduledTimeArr: String? = null,
        @Query("arr_scheduled_time_dep") arrScheduledTimeDep: String? = null,
    ): Response<Flight_api_response>
}