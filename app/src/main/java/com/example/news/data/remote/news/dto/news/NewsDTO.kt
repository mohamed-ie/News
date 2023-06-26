package com.example.news.data.remote.news.dto.news

import com.google.gson.annotations.SerializedName

data class NewsDTO(

    @field:SerializedName("totalResults")
	val totalResults: Int,

    @field:SerializedName("articles")
	val articles: List<ArticleDTO>,

    @field:SerializedName("status")
	val status: String
)