package com.example.practicaltest.app.ui

import android.os.Bundle
import com.example.practicaltest.app.dependencyinjction.injectFeature
import com.example.practicaltest.core.ui.BaseActivity
import com.example.practicaltest.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        injectFeature()
    }
}