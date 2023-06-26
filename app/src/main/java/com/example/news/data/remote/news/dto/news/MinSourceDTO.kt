package com.example.news.data.remote.news.dto.news

import com.google.gson.annotations.SerializedName

data class MinSourceDTO(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String? = null
)