package com.example.news.data.remote.news.dto.source

import com.google.gson.annotations.SerializedName

data class SourcesDTO(

    @field:SerializedName("sources")
	val sources: List<SourceDTO>,

    @field:SerializedName("status")
	val status: String
)