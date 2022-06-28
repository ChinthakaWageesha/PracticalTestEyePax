package com.example.practicaltest.app.presentation.topNews

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicaltest.R
import com.example.practicaltest.core.presentation.BaseActivity
import com.example.practicaltest.databinding.ActivityTopNewsBinding

class TopNewsActivity : BaseActivity() {

    private lateinit var binding: ActivityTopNewsBinding
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var topNewsAdapter: TopNewsAdapter

    override val toolBarTitle: String
        get() = getString(R.string.label_hot_updates)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAdapter()
    }

    private fun setAdapter() {
        topNewsAdapter = TopNewsAdapter()
        layoutManager = LinearLayoutManager(this)
        binding.rvTopNews.adapter = topNewsAdapter
        binding.rvTopNews.layoutManager = layoutManager
    }
}