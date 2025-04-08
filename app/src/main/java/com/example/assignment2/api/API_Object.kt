package com.example.assignment2.api
import com.example.assignment2.constant.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object API_Object {
    private val retrofit by lazy {
        Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val original = chain.request()
                        val requestBuilder = original.newBuilder()
                            .header("Authorization", "Bearer ${Constants.API_KEY}")
                        val request = requestBuilder.build()
                        chain.proceed(request)
                    }
                    .build()
            )
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiInterface: API_Interface by lazy {
        retrofit.create(API_Interface::class.java)
    }
}