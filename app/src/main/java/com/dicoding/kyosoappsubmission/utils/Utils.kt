package com.dicoding.kyosoappsubmission.utils

import java.text.NumberFormat
import java.util.*

fun numberFormat(number: Int): String {
    return NumberFormat.getNumberInstance(Locale.US).format(number)
}