package com.example.nikeapplication.injection

import android.content.Context
import com.example.nikeapplication.model.db.AppDatabase
import com.example.nikeapplication.model.networking.RetrofitBuilder
import com.example.nikeapplication.model.networking.UrbanDictionaryRepository
import com.example.nikeapplication.model.networking.UrbanDictionaryRepositoryImpl
import com.example.nikeapplication.model.networking.UrbanDictionaryService

class Injection {
    private var retrofit: UrbanDictionaryService?= null
    private var dataBaseInstance: AppDatabase?= null

    fun provideUserRepo(context: Context): UrbanDictionaryRepository {
        return UrbanDictionaryRepositoryImpl(provideUrbanRestService(), provideAppDatabase(context))
    }

    private fun provideUrbanRestService(): UrbanDictionaryService {
        if (retrofit == null) {
            retrofit = RetrofitBuilder().getRetrofit()
        }
        return retrofit as UrbanDictionaryService
    }

    private fun provideAppDatabase(context: Context): AppDatabase {
        if (dataBaseInstance == null) {
            dataBaseInstance = AppDatabase.getDatabaseInstance(context)
        }
        return dataBaseInstance as AppDatabase
    }
}