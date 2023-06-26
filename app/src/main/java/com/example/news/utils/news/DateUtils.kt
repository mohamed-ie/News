package com.example.news.utils.news

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class DateUtils private constructor(){
    companion object {
        fun dateToHumanString(
            pattern: String,
            source: String,
            locale: Locale = Locale.getDefault()
        ): String {
            val sourceCalender = SimpleDateFormat(pattern, locale).apply { parse(source) }.calendar
            val currentCalendar = Calendar.getInstance()

            val currentYear = currentCalendar.get(Calendar.YEAR)
            val currentMonth = currentCalendar.get(Calendar.MONTH)
            val currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH)
            val currentHour = currentCalendar.get(Calendar.HOUR_OF_DAY)
            val currentMinute = currentCalendar.get(Calendar.MINUTE)

            val articleYear = sourceCalender.get(Calendar.YEAR)
            val articleMonth = sourceCalender.get(Calendar.MONTH)
            val articleDay = sourceCalender.get(Calendar.DAY_OF_MONTH)
            val articleHour = sourceCalender.get(Calendar.HOUR_OF_DAY)
            val articleMinute = sourceCalender.get(Calendar.MINUTE)

            with(currentYear - articleYear) {
                if (this == 1)
                    return "from 1 year"
                if (this > 1)
                    return "from $this years"
            }

            with(currentMonth - articleMonth) {
                if (this == 1)
                    return "from 1 month"
                if (this > 1)
                    return "from $this months"
            }

            with(currentDay - articleDay) {
                if (this == 1)
                    return "yesterday"
                if (this > 1)
                    return "from $this days"
            }

            with(currentHour - articleHour) {
                if (this == 1)
                    return "from 1 hour"
                if (this in 2..12)
                    return "from $this hours"
                if (this > 12)
                    return "today"
            }

            with(currentMinute - articleMinute) {
                if (this == 1)
                    return "from 1 minute"
                if (this in 2..12)
                    return "from $this minutes"
            }

            return "now"
        }

        fun toHumanDate(
            outPattern: String,
            date: Date = Calendar.getInstance().time,
            locale: Locale = Locale.getDefault()
        ): String {
            return SimpleDateFormat(outPattern, locale).format(date)
        }
    }
}