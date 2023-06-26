package com.example.news.domain.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

data class Country(
    val code: String,
    val name: String,
    val flag: String,
    val nativeName: String
)
