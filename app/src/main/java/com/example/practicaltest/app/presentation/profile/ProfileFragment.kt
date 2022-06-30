package com.example.practicaltest.app.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.practicaltest.core.presentation.BaseFragment
import com.example.practicaltest.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated() {
        initLayout()
    }

    private fun initLayout() {

    }

    companion object {
        const val TAG = "Profile"
        fun newInstance() = ProfileFragment()
    }

}