package com.sample.newsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sample.newsapp.NewsActivity
import com.sample.newsapp.R
import com.sample.newsapp.adapters.NewsAdapter
import com.sample.newsapp.utils.Resource
import com.sample.newsapp.viewmodels.NewsViewModel
import kotlinx.android.synthetic.main.fragment_breaking_news.*

class BreakingNewsFragment :Fragment(R.layout.fragment_breaking_news) {
    lateinit var viewModel :NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =(activity as NewsActivity).viewmodel
        setUpRecyclerView()
        viewModel.news.observe(viewLifecycleOwner, Observer {response ->
            when(response){
                is Resource.Success ->{
                    hideProgress()
                    response.data?.let {newsResponse->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error ->{
                    hideProgress()
                    response.message?.let { message ->
                        Log.e("Error",message)
                    }
                }
                is Resource.Loading ->{
                    showProgress()
                }
            }

        })
    }
    private fun setUpRecyclerView(){
        newsAdapter = NewsAdapter()
        recyclerViewBreakingNews.apply {
            adapter = newsAdapter
        }

    }
    private fun hideProgress(){
        loader_breakingNews.visibility = View.GONE
        recyclerViewBreakingNews.visibility = View.VISIBLE
    }
    private fun showProgress(){
        recyclerViewBreakingNews.visibility = View.GONE
        loader_breakingNews.visibility = View.VISIBLE

    }
}