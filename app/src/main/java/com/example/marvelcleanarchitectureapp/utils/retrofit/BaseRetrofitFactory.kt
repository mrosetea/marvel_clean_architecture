package com.example.marvelcleanarchitectureapp.utils.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BaseRetrofitFactory {
    private val baseUrl = "https://gateway.marvel.com/v1/public/";
    fun <T> create(apiInterface: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(apiInterface)
    }

}