package com.example.nikeapplication.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeapplication.R
import com.example.nikeapplication.model.Item
import kotlinx.android.synthetic.main.list_item.view.*

class MyRecyclerViewAdapter: RecyclerView.Adapter<MyRecyclerViewAdapter.MyCustomViewHolder>() {

    var items: List<Item> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class MyCustomViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val definition = view.tv_definition
        private val thumbsUp = view.tv_thumbs_up
        private val thumbsDown = view.tv_thumbs_down

        fun bind(item: Item) {
            definition.text = item.definition
            thumbsUp.text = item.thumbs_up.toString()
            thumbsDown.text = item.thumbs_down.toString()
        }
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