package com.example.practicaltest.app.presentation.newsfeed

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicaltest.core.extenstion.clearTextOnRightDrawableClick
import com.example.practicaltest.core.extenstion.showToast
import com.example.practicaltest.core.extenstion.validateOnTextChange
import com.example.practicaltest.core.general.GoTo
import com.example.practicaltest.core.presentation.BaseFragment
import com.example.practicaltest.databinding.FragmentNewsFeedBinding
import android.view.inputmethod.EditorInfo
import com.example.practicaltest.app.domain.model.DArticle
import com.example.practicaltest.app.presentation.newsfeed.adapter.LatestNewsAdapter
import com.example.practicaltest.app.presentation.newsfeed.adapter.NewsAdapter
import com.example.practicaltest.app.presentation.newsfeed.adapter.NewsCategoryAdapter
import com.example.practicaltest.core.util.Resource
import com.example.practicaltest.core.util.ResourceState
import org.koin.android.viewmodel.ext.android.viewModel

class NewsFeedFragment : BaseFragment(), (String) -> Unit{

    private lateinit var binding: FragmentNewsFeedBinding
    private lateinit var latestNewsAdapter: LatestNewsAdapter
    private lateinit var categoryList: ArrayList<String>
    private lateinit var categoryAdapter: NewsCategoryAdapter
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private val vmNews by viewModel<NewsViewModel>()

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
        initSearch()
        getLatestNews()
        setCategoryAdapter()
        setNewsAdapter()
        binding.txtSeeAll.setOnClickListener { GoTo.topNews(requireContext()) }
    }

    private fun initSearch(){
        binding.etSearch.validateOnTextChange(isCheckValidateIcon = true) { s -> s.isNotEmpty() }
        binding.etSearch.clearTextOnRightDrawableClick()
        binding.etSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (v.text.toString().isEmpty()){
                    resetBreakingNewsSection()
                } else {
                    v.text.toString().showToast(requireContext())
                    setNewsSearch()
                }
            }
            false
        }
    }

    private fun getLatestNews(){
        vmNews.getLatestNews()
        vmNews.liveDataLatestNews.observe(requireActivity(), { observerGetLatestNews(it) })
    }

    private fun observerGetLatestNews(resource: Resource<List<DArticle>>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    setLatestNewsAdapter(it.data!!.toMutableList())
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    it.message?.showToast(requireContext())
                }
            }
        }
    }

    private fun setLatestNewsAdapter(latestNewsList: MutableList<DArticle>) {
        latestNewsAdapter = LatestNewsAdapter(latestNewsList)
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

    private fun resetBreakingNewsSection() {
        binding.clLatestNewsBase.visibility = View.VISIBLE
        binding.txtNewsResultsCount.visibility = View.GONE
        binding.imgNotifications.visibility = View.VISIBLE
    }

    @SuppressLint("SetTextI18n")
    private fun setNewsSearch() {
        binding.clLatestNewsBase.visibility = View.GONE
        binding.txtNewsResultsCount.visibility = View.VISIBLE
        binding.imgNotifications.visibility = View.GONE
        binding.txtNewsResultsCount.text = "About 23232 results for ${binding.etSearch.text}"
    }

    companion object {
        const val TAG = "NewsFeed"
        fun newInstance() = NewsFeedFragment()
    }

    override fun invoke(category: String) {
        category.showToast(requireContext())
    }

}