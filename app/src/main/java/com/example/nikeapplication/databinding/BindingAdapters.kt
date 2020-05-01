package com.example.nikeapplication.databinding

import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
    val itemDecoration = DividerItemDecoration(view.context, LinearLayout.VERTICAL)
    view.addItemDecoration(itemDecoration)
}

@BindingAdapter("liveDataVisibility")
fun setVisibility(view: View,  visibility: LiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer {
                value -> view.visibility = value?:View.VISIBLE
        })
    }
}