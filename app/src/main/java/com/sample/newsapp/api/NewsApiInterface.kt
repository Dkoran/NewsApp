package com.sample.newsapp.api

import com.sample.newsapp.models.NewsResponse
import com.sample.newsapp.utils.Constants.Companion.apiKey
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiInterface {

    @GET("v2/top-headlines")
    suspend fun getHeadlinesNews(
            @Query ("country")
            country_code :String = "us",
            @Query("page")
            pageNumber:Int = 1,
            @Query("apiKey")
            api_key :String = apiKey

    ):Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchNews(@Query("q") searchQuery:String, @Query("page") pageNumber: Int = 1, @Query("apiKey") api_key: String = apiKey):Response<NewsResponse>
}