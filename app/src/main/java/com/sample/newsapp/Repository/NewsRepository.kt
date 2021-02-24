package com.sample.newsapp.Repository

import com.sample.newsapp.api.RetrofitInstance
import com.sample.newsapp.db.NewsDatabase

class NewsRepository(val db: NewsDatabase) {

    suspend fun getNews(countrycode: String, page: Int) =
            RetrofitInstance.apiService.getHeadlinesNews(countrycode, page)

    suspend fun searchNews(search: String, pageNumber: Int) =
            RetrofitInstance.apiService.searchNews(search,pageNumber)
}