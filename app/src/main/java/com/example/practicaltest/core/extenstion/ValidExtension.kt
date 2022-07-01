package com.example.practicaltest.core.extenstion

import android.util.Patterns

fun String.isValidEmail(): Boolean = this.isNotEmpty() &&
        Patterns.EMAIL_ADDRESS.matcher(this.trim()).matches()

fun String.isValidPassword(): Boolean {
    var valid: Boolean
    try {
        valid = this.length >= 8
    } catch (e: Exception) {
        valid = false
        e.printStackTrace()
    }

    return valid
}