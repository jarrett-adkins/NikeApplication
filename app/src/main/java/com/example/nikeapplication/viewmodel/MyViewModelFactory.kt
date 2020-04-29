package com.example.nikeapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nikeapplication.model.networking.UrbanDictionaryRepository

class MyViewModelFactory(private val urbanDictionaryRepository: UrbanDictionaryRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyViewModel(urbanDictionaryRepository) as T
    }
}