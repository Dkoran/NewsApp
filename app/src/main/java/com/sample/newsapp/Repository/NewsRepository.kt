package com.sample.newsapp.Repository

import com.sample.newsapp.api.RetrofitInstance
import com.sample.newsapp.db.NewsDatabase
import com.sample.newsapp.models.ArticlesItem

class NewsRepository(val db: NewsDatabase) {

    suspend fun getNews(countrycode: String, page: Int) =
            RetrofitInstance.apiService.getHeadlinesNews(countrycode, page)

    suspend fun searchNews(search: String, pageNumber: Int) =
            RetrofitInstance.apiService.searchNews(search,pageNumber)

    suspend fun insertArticle(article: ArticlesItem) = db.getNewsDao().insertArticle(article)

    fun getSavedNews() = db.getNewsDao().getSavedArticle()

    suspend fun deleteArticle(article: ArticlesItem) = db.getNewsDao().deleteArticle(article)
}