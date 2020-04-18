package com.example.nikeapplication.model.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    fun getRetrofit (baseUrl: String): UrbanDictionaryService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UrbanDictionaryService::class.java)
    }
}