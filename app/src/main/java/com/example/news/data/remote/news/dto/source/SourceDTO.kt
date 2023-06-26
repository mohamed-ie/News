package com.example.news.data.remote.news.dto.source

import com.example.news.ui.common.Source
import com.google.gson.annotations.SerializedName

data class SourceDTO(

    @field:SerializedName("country")
    val country: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("language")
    val language: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("category")
    val category: String,

    @field:SerializedName("url")
    val url: String
) {
    fun toSource(isSelected: Boolean) = Source(
        country = country,
        name = name,
        description = description,
        language = language,
        category = category,
        url = url,
        id = id,
        isSelected = isSelected
    )
}