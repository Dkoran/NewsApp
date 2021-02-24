package com.sample.newsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.newsapp.Repository.NewsRepository
import com.sample.newsapp.viewmodels.NewsViewModel

class NewsViewModelProvider(val newsRepository: NewsRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return NewsViewModel(newsRepository ) as T
    }
}