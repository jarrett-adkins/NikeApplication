package com.example.nikeapplication.model.db

import androidx.room.*
import com.example.nikeapplication.model.Item
import io.reactivex.Single

@Dao
interface WordDAO {

    @Query("SELECT * FROM items WHERE term = :search")
    fun getDefinitions(search: String): Single<MutableList<Item>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: MutableList<Item>)

    @Insert
    fun insert(item: Item)

    @Delete
    fun delete(item: Item)

    @Update
    fun updateWord(item: Item)
}