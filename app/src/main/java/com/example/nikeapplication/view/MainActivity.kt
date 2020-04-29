package com.example.nikeapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nikeapplication.R
import com.example.nikeapplication.databinding.ActivityMainBinding
import com.example.nikeapplication.injection.Injection
import com.example.nikeapplication.viewmodel.MyViewModel
import com.example.nikeapplication.viewmodel.MyViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val injection = Injection()
    private val viewModel by lazy { ViewModelProvider(this, MyViewModelFactory(injection.provideUserRepo(this.applicationContext))).get(MyViewModel::class.java) }
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.recyclerView?.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false)
        binding?.let {
            it.viewModel = viewModel
        }

        initButtons()
        wordSearch()
    }

    private fun initButtons() {
        viewModel.itemLiveData.observe(this, Observer {
            btn_sort_thumbs_up.isEnabled = it.isNotEmpty()
            btn_sort_thumbs_down.isEnabled = it.isNotEmpty()
        })

        btn_sort_thumbs_up.setOnClickListener {
            viewModel.adapter.sortByThumbsUp()
        }

        btn_sort_thumbs_down.setOnClickListener {
            viewModel.adapter.sortByThumbsDown()
        }
    }

    private fun wordSearch() {
        viewModel.doSearch(word_search)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        viewModel.itemLiveData.observe(this, Observer {
            viewModel.adapter.items = it
        })
    }
}