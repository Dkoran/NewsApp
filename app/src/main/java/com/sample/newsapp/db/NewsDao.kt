package com.sample.newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sample.newsapp.models.ArticlesItem

@Dao
interface NewsDao {
     @Insert( onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertArticle(articlesItem: ArticlesItem):Long

    @Query("SELECT * FROM article")
    fun getSavedArticle():LiveData<List<ArticlesItem>>

    @Delete
    suspend fun deleteArticle(articlesItem: ArticlesItem)
}