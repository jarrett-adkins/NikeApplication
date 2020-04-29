package com.example.nikeapplication.databinding

import android.view.View
import android.widget.TextView
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

@BindingAdapter("text")
fun setText(view: TextView,  text: LiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value?:""})
    }
}