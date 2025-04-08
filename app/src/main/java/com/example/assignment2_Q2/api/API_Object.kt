package com.example.assignment2_Q2.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.assignment2_Q2.Constants.Constants
import okhttp3.OkHttpClient

object API_Object {
    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-rapidapi-key", Constants.API_KEY)
                .addHeader("x-rapidapi-host", Constants.RAPIDAPI_HOST)
                .build()
            chain.proceed(request)
        }
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: API_Interface by lazy {
        retrofit.create(API_Interface::class.java)
    }
}
