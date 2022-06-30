package com.example.practicaltest.app.presentation.newsfeed

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicaltest.core.general.GoTo
import com.example.practicaltest.core.presentation.BaseFragment
import com.example.practicaltest.databinding.FragmentNewsFeedBinding
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import com.example.practicaltest.apiclient.apiSupport.response.NewsResponse
import com.example.practicaltest.app.dependencyinjction.mapToDomain
import com.example.practicaltest.app.domain.model.DArticle
import com.example.practicaltest.app.presentation.newsfeed.adapter.LatestNewsAdapter
import com.example.practicaltest.app.presentation.newsfeed.adapter.NewsAdapter
import com.example.practicaltest.app.presentation.newsfeed.adapter.NewsCategoryAdapter
import com.example.practicaltest.core.extenstion.*
import com.example.practicaltest.core.util.Msg
import com.example.practicaltest.core.util.Resource
import com.example.practicaltest.core.util.ResourceState
import org.koin.android.ext.android.bind
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

class NewsFeedFragment : BaseFragment(), (String) -> Unit {

    private lateinit var binding: FragmentNewsFeedBinding
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
        getNews()
        setCategoryAdapter()
        binding.txtSeeAll.setOnClickListener { GoTo.topNews(requireContext()) }
    }

    private fun initSearch() {
        binding.etSearch.validateOnTextChange(isCheckValidateIcon = true) { s -> s.isNotEmpty() }

        binding.etSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(sequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (sequence.toString().isEmpty()) {
                    resetNewsFeed()
                } else {
                    setNewsSearch(sequence.toString())
                }
            }

            override fun afterTextChanged(p0: Editable?) {}

        })

    }

    private fun getLatestNews() {
        callLatestNewsService()
        vmNews.liveDataLatestNews.observe(requireActivity(), { observerGetLatestNews(it) })
    }

    private fun callLatestNewsService() {
        context?.withNetwork({
            vmNews.getLatestNews()
        }, {
            Msg.INTERNET_ISSUE.showToast(requireContext())
        })
    }

    private fun observerGetLatestNews(resource: Resource<List<DArticle>>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    binding.rvLatestNews.setEmptyView(binding.txtNoDataLatestNews, it.data?.size!!)
                    setLatestNewsAdapter(it.data.toMutableList())
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    binding.rvLatestNews.setEmptyView(binding.txtNoDataLatestNews, 0)
                    it.message?.showToast(requireContext())
                }
            }
        }
    }

    private fun setLatestNewsAdapter(latestNewsList: MutableList<DArticle>) {
        binding.rvLatestNews.adapter = LatestNewsAdapter(latestNewsList)
        binding.rvLatestNews.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun getNews() {
        callGetNewsService()
        binding.etSearch.requestFocus()
        vmNews.liveDataNews.observe(requireActivity(), { observerGetNews(it) })
    }

    private fun callGetNewsService() {
        context?.withNetwork({
            vmNews.getNews()
        }, {
            Msg.INTERNET_ISSUE.showToast(requireContext())
        })
    }

    @SuppressLint("SetTextI18n")
    private fun observerGetNews(resource: Resource<NewsResponse>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    binding.txtNewsResultsCount.text =
                        "About ${it.totalResults} results for ${binding.etSearch.text}"
                    binding.rvNews.setEmptyView(binding.txtNoDataNews, it.data!!.data?.size!!)
                    setNewsAdapter(it.data.data!!.map { it.mapToDomain() }.toMutableList())
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    binding.rvNews.setEmptyView(binding.txtNoDataNews, 0)
                    it.message?.showToast(requireContext())
                }
            }
        }
    }

    private fun setCategoryAdapter() {
        val categoryList: ArrayList<String> = arrayListOf()
        categoryList.add("Category")
        categoryList.add("Language")
        categoryList.add("Country")

        binding.rvCategories.adapter = NewsCategoryAdapter(categoryList, this)
        binding.rvCategories.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setNewsAdapter(newsList: MutableList<DArticle>) {
        binding.rvNews.adapter = NewsAdapter(newsList)
        binding.rvNews.layoutManager = LinearLayoutManager(context)
    }

    private fun resetNewsFeed() {
        vmNews.searchKey = null
        callGetNewsService()
        binding.clLatestNewsBase.visibility = View.VISIBLE
        binding.txtNewsResultsCount.visibility = View.GONE
        binding.imgNotifications.visibility = View.VISIBLE
    }

    @SuppressLint("SetTextI18n")
    private fun setNewsSearch(searchKey: String) {
        vmNews.searchKey = searchKey
        callGetNewsService()
        binding.txtNewsResultsCount.visibility = View.VISIBLE
        binding.clLatestNewsBase.visibility = View.GONE
        binding.imgNotifications.visibility = View.GONE
    }

    companion object {
        const val TAG = "NewsFeed"
        fun newInstance() = NewsFeedFragment()
    }

    override fun invoke(category: String) {
        //category.showToast(requireContext())
    }

}