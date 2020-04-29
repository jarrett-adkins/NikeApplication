package com.example.nikeapplication.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nikeapplication.model.Item

const val DB_VERSION = 1
const val DB_NAME = "itemDB.db"

@Database(entities = [Item::class], version = DB_VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDAO

    companion object {
        @Volatile
        private var databseInstance: AppDatabase? = null

        fun getDatabaseInstance(mContext: Context): AppDatabase =
            databseInstance ?: synchronized(this) {
                databseInstance ?: buildDatabaseInstance(mContext).also {
                    databseInstance = it
                }
            }

        private fun buildDatabaseInstance(mContext: Context) =
            Room.databaseBuilder(mContext, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}