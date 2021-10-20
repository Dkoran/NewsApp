package com.sample.newsapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.sample.newsapp.Repository.NewsRepository
import com.sample.newsapp.models.ArticlesItem
import com.sample.newsapp.models.NewsResponse
import com.sample.newsapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(val repository: NewsRepository):  ViewModel() {
    val news: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val newsPage = 1

    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val searchNewsPage = 1
    init {
        getNews("us")
    }

    fun getNews(country: String) = viewModelScope.launch {
        news.postValue(Resource.Loading())
        val response = repository.getNews(country,newsPage)
       news.postValue(handelResponse(response))
    }
    fun search(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val searchResponse = repository.searchNews(searchQuery,searchNewsPage)
        searchNews.postValue(handelSearchNewsResponse(searchResponse))
    }
    fun saveArticle(article: ArticlesItem) = viewModelScope.launch {
        repository.insertArticle(article)
    }

    fun getSavedNews() = repository.getSavedNews()

    fun deleteArticle(article: ArticlesItem) = viewModelScope.launch {
        repository.deleteArticle(article)
    }

    private fun handelResponse(response: Response<NewsResponse>): Resource<NewsResponse>{
        if(response.isSuccessful){
            response.body()?.let {resultsResponse->
                return Resource.Success(resultsResponse)

            }
        }
        return Resource.Error(response.message())

    }

    private fun handelSearchNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse>{
        if(response.isSuccessful){
            response.body()?.let {resultsResponse->
                return Resource.Success(resultsResponse)

            }
        }
        return Resource.Error(response.message())

    }
}