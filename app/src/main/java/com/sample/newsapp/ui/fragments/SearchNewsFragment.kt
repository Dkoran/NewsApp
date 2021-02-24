package com.sample.newsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sample.newsapp.NewsActivity
import com.sample.newsapp.R
import com.sample.newsapp.adapters.NewsAdapter
import com.sample.newsapp.utils.Constants.Companion.searchDelay
import com.sample.newsapp.utils.Resource
import com.sample.newsapp.viewmodels.NewsViewModel
import kotlinx.android.synthetic.main.fragment_breaking_news.*
import kotlinx.android.synthetic.main.fragment_search_news.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchNewsFragment : Fragment (R.layout.fragment_search_news) {
    lateinit var viewModel: NewsViewModel
    lateinit var searchAdapter : NewsAdapter
    var job: Job? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel =(activity as NewsActivity).viewmodel
        setUpRecyclerView()
        ed_Search.addTextChangedListener {edSearch->
            job?.cancel()
            job = MainScope().launch {
                delay(searchDelay)
                edSearch?.let {
                    if (edSearch.toString().isNotEmpty()){
                        viewModel.search(edSearch.toString())
                    }


                }
            }

        }
        viewModel.searchNews.observe(viewLifecycleOwner, Observer {response ->
            when(response){
                is Resource.Success ->{
                    hideProgress()
                    response.data?.let {newsResponse->
                        searchAdapter.differ.submitList(newsResponse.articles)
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
        searchAdapter = NewsAdapter()
        recyclerView_Search.apply {
            adapter = searchAdapter
        }

    }
    private fun hideProgress(){
        loader_SearchNews.visibility = View.GONE
        recyclerView_Search.visibility = View.VISIBLE
    }
    private fun showProgress(){
        recyclerView_Search.visibility = View.GONE
        loader_SearchNews.visibility = View.VISIBLE

    }
}