package com.example.nikeapplication.model.networking

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    fun getRetrofit (): UrbanDictionaryService {
        return Retrofit.Builder()
            .baseUrl("https://mashape-community-urban-dictionary.p.rapidapi.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UrbanDictionaryService::class.java)
    }
}