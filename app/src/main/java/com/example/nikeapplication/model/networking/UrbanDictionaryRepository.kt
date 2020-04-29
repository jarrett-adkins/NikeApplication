package com.example.nikeapplication.model.networking

import com.example.nikeapplication.model.Item
import io.reactivex.Single

interface UrbanDictionaryRepository {
    fun getDefinitionList(term: String): Single<MutableList<Item>>
}