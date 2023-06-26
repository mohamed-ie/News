package com.example.news.common

import com.example.news.R

interface ArticlesRetrievalCount {
    companion object {
        val OPTIONS = listOf(
            R.string.twenty,
            R.string.fourty,
            R.string.sixty,
            R.string.eighty,
            R.string.hundred
        )

        fun from(option: Int): Byte = when (option) {
            OPTIONS[0] -> 20
            OPTIONS[1] -> 40
            OPTIONS[2] -> 60
            OPTIONS[3] -> 80
            else ->  100
        }

    }
}