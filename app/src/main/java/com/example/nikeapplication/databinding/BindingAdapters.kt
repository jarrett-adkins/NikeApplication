package com.example.nikeapplication.databinding

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

@BindingAdapter("liveDataVisibility")
fun setVisibility(view: View,  visibility: LiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer {
                value -> view.visibility = value?:View.VISIBLE
        })
    }
}