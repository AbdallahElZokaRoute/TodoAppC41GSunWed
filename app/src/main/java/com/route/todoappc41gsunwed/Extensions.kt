package com.route.todoappc41gsunwed

import java.util.Calendar

fun Calendar.setDate(dayOfMonth: Int, month: Int, year: Int) {
    set(Calendar.YEAR, year)
    set(Calendar.MONTH, month)
    set(Calendar.DAY_OF_MONTH, dayOfMonth)
}

fun Calendar.clearTime() {
    set(Calendar.HOUR, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
    set(Calendar.MILLISECOND, 0)

}

