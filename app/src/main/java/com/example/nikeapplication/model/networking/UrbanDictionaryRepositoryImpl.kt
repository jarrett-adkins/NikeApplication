package com.example.nikeapplication.model.networking

import com.example.nikeapplication.model.Item
import com.example.nikeapplication.model.db.AppDatabase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UrbanDictionaryRepositoryImpl(private val urbanDictionaryService: UrbanDictionaryService,
                                    private val appDatabase: AppDatabase): UrbanDictionaryRepository {

    override fun getDefinitionList(term: String): Single<MutableList<Item>> {
        return getDefinitionFromDB(term)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getDefinitionFromDB(term: String): Single<MutableList<Item>> {
        return appDatabase
            .wordDao()
            .getDefinitions(term)
            .flatMap { localList ->
                if (localList.isEmpty()) {
                    println("_xyz noData $term")
                    getDefinitionFromRemote(term).map { remoteList ->
                        println("_xyz getData from remote1 $term")
                        cacheTerm(remoteList)
                        remoteList
                    }
                } else {
                    println("_xyz getData from db $term")
                    Single.just(localList)
                }
            }
    }

    private fun getDefinitionFromRemote(term: String): Single<MutableList<Item>> {
        return urbanDictionaryService
            .getItems(term)
            .map {
                it.list.forEach { item ->
                    item.term = term
                }
                println("_xyz getData from remote2 $term")
                it.list
            }
    }

    private fun cacheTerm(items: MutableList<Item>) {
        println("_xyz cache = $items")
        appDatabase.wordDao().insertAll(items)
    }
}