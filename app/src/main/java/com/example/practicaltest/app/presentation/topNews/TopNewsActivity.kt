package com.example.practicaltest.app.presentation.topNews

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicaltest.R
import com.example.practicaltest.app.domain.model.DArticle
import com.example.practicaltest.app.presentation.newsfeed.NewsViewModel
import com.example.practicaltest.core.extenstion.setEmptyView
import com.example.practicaltest.core.extenstion.showToast
import com.example.practicaltest.core.extenstion.withNetwork
import com.example.practicaltest.core.presentation.BaseActivity
import com.example.practicaltest.core.util.Msg
import com.example.practicaltest.core.util.Resource
import com.example.practicaltest.core.util.ResourceState
import com.example.practicaltest.databinding.ActivityTopNewsBinding
import org.koin.android.viewmodel.ext.android.viewModel

class TopNewsActivity : BaseActivity() {

    private lateinit var binding: ActivityTopNewsBinding
    private val vmNews by viewModel<NewsViewModel>()

    override val toolBarTitle: String
        get() = getString(R.string.label_hot_updates)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getLatestNews()
    }

    private fun getLatestNews() {
        withNetwork({
            vmNews.getLatestNews()
        }, {
            Msg.INTERNET_ISSUE.showToast(this)
        })

        vmNews.liveDataLatestNews.observe(this, { observerGetLatestNews(it) })
    }

    private fun observerGetLatestNews(resource: Resource<List<DArticle>>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    binding.rvTopNews.setEmptyView(binding.txtNoData, it.data!!.size)
                    setAdapter(it.data.toMutableList())
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    binding.rvTopNews.setEmptyView(binding.txtNoData, 0)
                    it.message?.showToast(this)
                }
            }
        }
    }

    private fun setAdapter(latestNewsList: MutableList<DArticle>) {
        binding.rvTopNews.adapter = TopNewsAdapter(latestNewsList)
        binding.rvTopNews.layoutManager = LinearLayoutManager(this)
    }
}