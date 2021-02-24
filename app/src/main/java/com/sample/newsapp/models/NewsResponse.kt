package com.sample.newsapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsResponse(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles: List<ArticlesItem>? = null,

	@field:SerializedName("status")
	val status: String? = null
):Serializable

@Entity(tableName = "article")
data class ArticlesItem(

	@PrimaryKey(autoGenerate = true)
	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("publishedAt")
	val publishedAt: String? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("urlToImage")
	val urlToImage: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("source")
	val source: Source? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("content")
	val content: String? = null
):Serializable

data class Source(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
):Serializable
