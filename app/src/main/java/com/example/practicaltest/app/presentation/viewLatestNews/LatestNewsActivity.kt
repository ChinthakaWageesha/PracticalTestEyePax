package com.example.practicaltest.app.presentation.viewLatestNews

import android.os.Bundle
import com.example.practicaltest.core.presentation.BaseActivity
import com.example.practicaltest.databinding.ActivityLatestNewsBinding

class LatestNewsActivity : BaseActivity() {

    private lateinit var binding: ActivityLatestNewsBinding

    override val toolBarTitle: String
        get() = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLatestNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}