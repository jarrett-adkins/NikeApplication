package com.example.nikeapplication.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeapplication.model.Item
import kotlinx.android.synthetic.main.list_item.view.*

class MyCustomViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val definition = view.tv_definition
    private val thumbsUp = view.tv_thumbs_up
    private val thumbsDown = view.tv_thumbs_down

    fun bind(item: Item) {
        definition.text = item.definition
        thumbsUp.text = item.thumbs_up.toString()
        thumbsDown.text = item.thumbs_down.toString()
    }
}