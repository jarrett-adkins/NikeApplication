package com.example.nikeapplication.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeapplication.R
import com.example.nikeapplication.model.Item

class MyRecyclerViewAdapter: RecyclerView.Adapter<MyCustomViewHolder>() {

    var items: List<Item> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return MyCustomViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MyCustomViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun sortByThumbsUp() {
        items = items.sortedByDescending { it.thumbs_up }
    }

    fun sortByThumbsDown() {
        items = items.sortedByDescending { it.thumbs_down }
    }
}