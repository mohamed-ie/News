package com.example.news.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.news.common.Constants
import com.example.news.utils.news.DateUtils

@Entity
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo("thumbnail_url")
    var thumbnailUrl: String?,

    @ColumnInfo("published_at")
    var publishedAt: String,

    var source: String,
    var author: String?,
    var title: String,
    var url: String,
    var content: String?,
    var description: String?,
    @Ignore
    var isBookmarked: Boolean = false,

    @Ignore
    var publishedFrom: String = DateUtils.dateToHumanString(
        Constants.NEWS_DATE_PATTERN,
        publishedAt
    ),
) {
    constructor() : this(0, "", "", "", "", "", "", "", "", true, "")
}
