package com.example.practicaltest.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.practicaltest.core.ui.BaseFragment
import com.example.practicaltest.databinding.FragmentNewsFeedBinding

class NewsFeedFragment : BaseFragment() {

    private lateinit var binding: FragmentNewsFeedBinding

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

    }

    companion object {
        const val TAG = "NewsFeed"
        fun newInstance() = NewsFeedFragment()
    }

}