package com.example.practicaltest.core.general

import android.content.Context
import android.content.Intent
import com.example.practicaltest.app.domain.model.DArticle
import com.example.practicaltest.app.presentation.MainActivity
import com.example.practicaltest.app.presentation.register.RegisterActivity
import com.example.practicaltest.app.presentation.latestNews.LatestNewsActivity
import com.example.practicaltest.app.presentation.topNews.TopNewsActivity
import com.example.practicaltest.core.util.IntentParcelable

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

    fun latestNews(context: Context, latestNews: DArticle) {
        val intent = Intent(context, LatestNewsActivity::class.java)
        intent.putExtra(IntentParcelable.LATEST_NEWS, latestNews )
        context.startActivity(intent)
    }

    fun topNews(context: Context) {
        context.startActivity(
            Intent(context, TopNewsActivity::class.java)
        )
    }
}