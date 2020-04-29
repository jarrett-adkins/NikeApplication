package com.example.nikeapplication.model.networking

import io.reactivex.Single
import com.example.nikeapplication.model.Response
import retrofit2.http.*

interface UrbanDictionaryService {
    @Headers("x-rapidapi-key: a5cd70b3b4msh473b7282f380383p1bf7cajsn32a09a0507e2")
    @GET("define")
    fun getItems(@Query("term") term: String) : Single<Response>
}