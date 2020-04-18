package com.example.nikeapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nikeapplication.model.Item
import com.example.nikeapplication.model.networking.RetrofitBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val BASE_URL = "https://mashape-community-urban-dictionary.p.rapidapi.com/"

class MyViewModel: ViewModel() {
    private var itemMutableLiveData = MutableLiveData<List<Item>>()
    val itemLiveData: LiveData<List<Item>>
        get() = itemMutableLiveData

    fun getDefinitions(searchTerm: String) {
        val retrofit = RetrofitBuilder().getRetrofit(BASE_URL)

        CoroutineScope(Dispatchers.IO).launch {
            val response = retrofit.getItems(searchTerm)
            withContext(Dispatchers.Main) {
                itemMutableLiveData.postValue(response.list)
            }
        }
    }

    fun sortByThumbsUp() {
        val list = itemMutableLiveData.value
        list?.let {
            itemMutableLiveData.postValue(it.sortedByDescending { it.thumbs_up })
        }
    }

    fun sortByThumbsDown() {
        val list = itemMutableLiveData.value
        list?.let {
            itemMutableLiveData.postValue(it.sortedBy { it.thumbs_up })
        }
    }
}