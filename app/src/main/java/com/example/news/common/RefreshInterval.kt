package com.example.news.common

import com.example.news.R

interface RefreshInterval {
    companion object {
       val OPTIONS = listOf(
            R.string.every_five_minutes,
            R.string.every_ten_minutes,
            R.string.every_twenty_minute
        )

        fun from(option: Int): Long {
            val min: Long = 60 * 1000

            return when (option) {
                OPTIONS[0] -> min * 5
                OPTIONS[1] -> min * 10
                OPTIONS[2] -> min * 20
                else -> min * 20
            }
        }
    }
}