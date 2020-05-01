package com.example.nikeapplication.model.networking

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    companion object {
        private var service: UrbanDictionaryService? = null

        fun getRetrofit (baseUrl: String): UrbanDictionaryService {
            if (service == null) {
                service = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(UrbanDictionaryService::class.java)
            }

            return service as UrbanDictionaryService
        }
    }
}
