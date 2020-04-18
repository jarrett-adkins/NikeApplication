package com.example.nikeapplication.model.networking

import com.example.nikeapplication.model.Payload
import retrofit2.http.*

interface UrbanDictionaryService {
    @Headers("x-rapidapi-key: a5cd70b3b4msh473b7282f380383p1bf7cajsn32a09a0507e2")
    @GET("define")
    suspend fun getItems(@Query("term") term: String) : Payload
}