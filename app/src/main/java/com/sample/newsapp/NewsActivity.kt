package com.sample.newsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.sample.newsapp.Repository.NewsRepository
import com.sample.newsapp.db.NewsDatabase
import com.sample.newsapp.ui.NewsViewModelProvider
import com.sample.newsapp.viewmodels.NewsViewModel
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {
    lateinit var viewmodel : NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val newsRepository = NewsRepository(NewsDatabase(this))
        val viewModelProviderFactory = NewsViewModelProvider(newsRepository)
        viewmodel = ViewModelProvider(this,viewModelProviderFactory).get(NewsViewModel::class.java)
        bottomNavigationView.setupWithNavController(newNavHostFragment.findNavController())
    }
}