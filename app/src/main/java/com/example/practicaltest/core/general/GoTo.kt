package com.example.practicaltest.core.general

import android.content.Context
import android.content.Intent
import com.example.practicaltest.app.presentation.MainActivity
import com.example.practicaltest.app.presentation.register.RegisterActivity
import com.example.practicaltest.app.presentation.viewLatestNews.LatestNewsActivity

object GoTo {

    fun register(context: Context) {
        context.startActivity(
            Intent(context, RegisterActivity::class.java)
        )
    }

    fun main(context: Context) {
        context.startActivity(
            Intent(context, MainActivity::class.java)
        )
    }

    fun latestNews(context: Context) {
        context.startActivity(
            Intent(context, LatestNewsActivity::class.java)
        )
    }
}