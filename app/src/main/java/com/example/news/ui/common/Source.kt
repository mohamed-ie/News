package com.example.news.ui.common

data class Source(
    val id: String,
    val country: String,
    val name: String,
    val description: String,
    val language: String,
    val category: String,
    val url: String,
    val isSelected: Boolean = false
)