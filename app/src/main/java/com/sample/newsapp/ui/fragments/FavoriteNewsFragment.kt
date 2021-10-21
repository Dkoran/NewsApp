package com.sample.newsapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.newsapp.NewsActivity
import com.sample.newsapp.R
import com.sample.newsapp.adapters.NewsAdapter
import com.sample.newsapp.viewmodels.NewsViewModel
import kotlinx.android.synthetic.main.fragment_favorite_news.*

class FavoriteNewsFragment :Fragment (R.layout.fragment_favorite_news) {
    lateinit var viewModel :NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =(activity as NewsActivity).viewmodel
        setupRecyclerView()

        viewModel.getSavedNews().observe(viewLifecycleOwner, Observer { articles ->
            newsAdapter.differ.submitList(articles)
        })
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter{}
        recyclerViewFavoriteNews.apply {
            adapter = newsAdapter
        }
    }
}