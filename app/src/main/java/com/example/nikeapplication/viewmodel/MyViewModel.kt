package com.example.nikeapplication.viewmodel

import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nikeapplication.model.Item
import com.example.nikeapplication.model.networking.UrbanDictionaryRepository
import com.example.nikeapplication.view.MyRecyclerViewAdapter
import com.jakewharton.rxbinding.support.v7.widget.RxSearchView
import io.reactivex.disposables.CompositeDisposable
import rx.Notification
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

class MyViewModel(private val urbanDictionaryRepository: UrbanDictionaryRepository): ViewModel() {
    private var itemMutableLiveData = MutableLiveData<List<Item>>()
    val itemLiveData: LiveData<List<Item>>
        get() = itemMutableLiveData
    val progressBarVisibilityMutableLiveData = MutableLiveData<Int>()
    val listVisibilityMutableLiveData = MutableLiveData<Int>()
    val listVisibilityLiveData: LiveData<Int>
        get() = listVisibilityMutableLiveData
    val progressBarVisibilityLiveData: LiveData<Int>
        get() = progressBarVisibilityMutableLiveData

    private val disposable = CompositeDisposable()
    val adapter = MyRecyclerViewAdapter()

    init {
        progressBarVisibilityMutableLiveData.value = View.GONE
    }

    fun doSearch(searchView: SearchView) {
        RxSearchView.queryTextChanges(searchView)
            .doOnEach { notification: Notification<in CharSequence?> ->
                val query = notification.value as CharSequence?
                if (query != null && !query.isBlank()) {
                    getDefinitions(query.toString())
                }
            }
            .debounce(
                300,
                TimeUnit.MILLISECONDS
            ) // to skip intermediate letters
            .retry(3)
            .subscribe()
    }

    private fun getDefinitions(searchTerm: String) {
        displayProgressBar()

        disposable.add(
            urbanDictionaryRepository
                .getDefinitionList(searchTerm)
                .subscribe({
                    itemMutableLiveData.postValue(it)
                    adapter.items = it
                    displayList()
                }, {
                    val errorString = when (it) {
                        is UnknownHostException -> "No Internet Connection"
                        else -> it.localizedMessage
                    }

                    Log.e("MyViewModel", "An error occurred while fetching results: $errorString", it)
                    displayList()
                })
        )
    }

    private fun displayList() {
        progressBarVisibilityMutableLiveData.value = View.GONE
        listVisibilityMutableLiveData.value = View.VISIBLE
    }

    private fun displayProgressBar() {
        progressBarVisibilityMutableLiveData.value = View.VISIBLE
        listVisibilityMutableLiveData.value = View.GONE
    }
}