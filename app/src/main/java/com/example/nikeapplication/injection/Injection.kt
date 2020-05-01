package com.example.nikeapplication.injection

import android.content.Context
import com.example.nikeapplication.R
import com.example.nikeapplication.model.db.AppDatabase
import com.example.nikeapplication.model.networking.RetrofitBuilder
import com.example.nikeapplication.model.networking.UrbanDictionaryRepository
import com.example.nikeapplication.model.networking.UrbanDictionaryRepositoryImpl
import com.example.nikeapplication.model.networking.UrbanDictionaryService

class Injection {
    private var retrofit: UrbanDictionaryService?= null
    private var dataBaseInstance: AppDatabase?= null

    fun provideUserRepo(context: Context): UrbanDictionaryRepository {
        return UrbanDictionaryRepositoryImpl(provideUrbanRestService(context), provideAppDatabase(context))
    }

    private fun provideUrbanRestService(context: Context): UrbanDictionaryService {
        if (retrofit == null) {
            retrofit = RetrofitBuilder.getRetrofit(context.getString(R.string.base_url))
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