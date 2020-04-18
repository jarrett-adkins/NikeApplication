package com.example.nikeapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nikeapplication.R
import com.example.nikeapplication.viewmodel.MyViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val adapter by lazy { MyRecyclerViewAdapter(this) }
    private val viewModel by lazy { ViewModelProviders.of(this).get(MyViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)

        btn_search.setOnClickListener { v ->
            if (!editText.text.toString().isBlank()) {
                // the web test of the api does not let you query empty strings, so neither am I.
                progress_bar.visibility = View.VISIBLE

                viewModel.getDefinitions(editText.text.toString())
                viewModel.itemLiveData.observe(this, Observer {
                    adapter.items = it
                    progress_bar.visibility = View.GONE
                    btn_sort_thumbs_up.isEnabled = true
                    btn_sort_thumbs_down.isEnabled = true
                })
            }
        }

        btn_sort_thumbs_up.setOnClickListener { v ->
            viewModel.sortByThumbsUp()
            viewModel.itemLiveData.observe(this, Observer {
                adapter.items = it
            })
        }

        btn_sort_thumbs_down.setOnClickListener { v ->
            viewModel.sortByThumbsDown()
            viewModel.itemLiveData.observe(this, Observer {
                adapter.items = it
            })
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        viewModel.itemLiveData.observe(this, Observer {
            adapter.items = it
        })
    }
}