package com.initvingadores.gamebook.util

import com.initvingadores.gamebook.util.Constants.Companion.EMAIL_PATTERN
import com.initvingadores.gamebook.util.Constants.Companion.PASSWORD_PATTERN
import java.util.regex.Pattern

fun String.isValidEmail (): Boolean {
    val pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE)
    return pattern.matcher(this).matches()
}

fun String.isValidPassword (): Boolean {
    val pattern = Pattern.compile(PASSWORD_PATTERN)
    val matcher = pattern.matcher(this)

    return matcher.matches() && (this.length in 6..15)
}

