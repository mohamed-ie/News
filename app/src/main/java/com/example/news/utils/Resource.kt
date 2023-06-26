package com.example.news.utils

sealed interface Resource<out D> {
    class Success<D>(val data: D) : Resource<D>
    class Error<D>(val error: UIError, val data: D? = null) : Resource<D>
}