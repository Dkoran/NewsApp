package com.sample.newsapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.sample.newsapp.NewsActivity
import com.sample.newsapp.R
import com.sample.newsapp.viewmodels.NewsViewModel

class FavoriteNewsFragment :Fragment (R.layout.fragment_favorite_news) {
    lateinit var viewModel :NewsViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =(activity as NewsActivity).viewmodel

    }
}