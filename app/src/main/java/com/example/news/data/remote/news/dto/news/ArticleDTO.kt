package com.example.news.data.remote.news.dto.news

import com.example.news.common.Constants
import com.example.news.data.local.db.entity.Article
import com.example.news.utils.news.DateUtils
import com.google.gson.annotations.SerializedName


data class ArticleDTO(

    @field:SerializedName("publishedAt")
    val publishedAt: String? = null,

    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("urlToImage")
    val urlToImage: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("source")
    val source: MinSourceDTO,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("content")
    val content: String? = null
) {
    fun toArticle(isBookmarked:Boolean) = Article(
        thumbnailUrl = urlToImage,
        source = source.name,
        publishedFrom = DateUtils.dateToHumanString(
            Constants.NEWS_DATE_TIME_PATTERN,
            publishedAt!!
        ),
        title = title,
        content = content,
        url = url,
        author = author,
        description = description,
        publishedAt = publishedAt,
        isBookmarked = isBookmarked
    )
}