package com.example.nikeapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Response(val list: MutableList<Item>)

@Entity(tableName = "items")
data class Item(@PrimaryKey(autoGenerate = true)
                var id: Int,
                @ColumnInfo(name = "term")
                var term: String,
                @ColumnInfo(name = "definition")
                val definition: String,
                @ColumnInfo(name = "thumbs_up")
                val thumbs_up: Int,
                @ColumnInfo(name = "thumbs_down")
                val thumbs_down: Int
)