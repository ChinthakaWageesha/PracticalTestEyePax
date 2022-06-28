package com.example.practicaltest.app.presentation.newsfeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicaltest.core.extenstion.showToast
import com.example.practicaltest.core.general.GoTo
import com.example.practicaltest.core.presentation.BaseFragment
import com.example.practicaltest.databinding.FragmentNewsFeedBinding

class NewsFeedFragment : BaseFragment(), (String) -> Unit {

    private lateinit var binding: FragmentNewsFeedBinding
    private lateinit var latestNewsAdapter: LatestNewsAdapter
    private lateinit var categoryList: ArrayList<String>
    private lateinit var categoryAdapter: NewsCategoryAdapter
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsFeedBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated() {
        initLayout()
    }

    private fun initLayout() {
        setLatestNewsAdapter()
        setCategoryAdapter()
        setNewsAdapter()
        binding.txtSeeAll.setOnClickListener { GoTo.topNews(requireContext()) }
    }

    private fun setLatestNewsAdapter() {
        latestNewsAdapter = LatestNewsAdapter()
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvLatestNews.adapter = latestNewsAdapter
        binding.rvLatestNews.layoutManager = layoutManager
    }

    private fun setCategoryAdapter() {
        categoryList = arrayListOf()
        categoryList.add("Category")
        categoryList.add("Language")
        categoryList.add("Country")

        categoryAdapter = NewsCategoryAdapter(categoryList, this)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategories.adapter = categoryAdapter
        binding.rvCategories.layoutManager = layoutManager
    }

    private fun setNewsAdapter() {
        newsAdapter = NewsAdapter()
        layoutManager = LinearLayoutManager(context)
        binding.rvNews.adapter = newsAdapter
        binding.rvNews.layoutManager = layoutManager
    }

    companion object {
        const val TAG = "NewsFeed"
        fun newInstance() = NewsFeedFragment()
    }

    override fun invoke(category: String) {
        category.showToast(requireContext())
    }

}