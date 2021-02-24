package com.sample.newsapp.api

import com.sample.newsapp.BuildConfig
import com.sample.newsapp.utils.Constants.Companion.baseUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        private val retrofit by lazy {
            val okHttpClientBuilder = OkHttpClient.Builder()
            if(BuildConfig.DEBUG){
                val logging = HttpLoggingInterceptor()
                logging.level =HttpLoggingInterceptor.Level.BODY
                okHttpClientBuilder.addInterceptor(logging)
            }
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClientBuilder.build())
                    .build()
        }

        val apiService by lazy {
            retrofit.create(NewsApiInterface::class.java)
        }
    }
}