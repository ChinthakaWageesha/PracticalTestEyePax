package com.example.practicaltest.app.presentation.latestNews

import android.annotation.SuppressLint
import android.os.Bundle
import com.example.practicaltest.app.domain.model.DArticle
import com.example.practicaltest.core.extenstion.loadImage
import com.example.practicaltest.core.presentation.BaseActivity
import com.example.practicaltest.core.util.IntentParcelable
import com.example.practicaltest.databinding.ActivityLatestNewsBinding

class LatestNewsActivity : BaseActivity() {

    private lateinit var binding: ActivityLatestNewsBinding
    private lateinit var latestNews: DArticle

    override val toolBarTitle: String
        get() = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLatestNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()
        setData()
    }

    private fun getData() {
        if (intent.hasExtra(IntentParcelable.LATEST_NEWS)) {
            latestNews = intent.getParcelableExtra(IntentParcelable.LATEST_NEWS)!!
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setData() {
        binding.txtLatestNewsPublishDay.text = latestNews.publishedAt!!.split("T")[0]
        binding.txtLatestNewsHeading.text = latestNews.title
        binding.txtLatestNewsPublisher.text = "Published by ${latestNews.author}"
        binding.txtLatestNewsContent.text = latestNews.content
        binding.imgLatestNews.loadImage(latestNews.urlToImage)

    }
}