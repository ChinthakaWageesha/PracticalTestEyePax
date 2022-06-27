package com.example.practicaltest.app.ui

import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import com.example.practicaltest.R
import com.example.practicaltest.app.dependencyinjction.injectFeature
import com.example.practicaltest.core.extenstion.currentFragment
import com.example.practicaltest.core.extenstion.replaceFragment
import com.example.practicaltest.core.extenstion.showToast
import com.example.practicaltest.core.ui.BaseActivity
import com.example.practicaltest.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private var doubleBackToExitPressedOnce = false

    override val toolBarTitle: String
        get() = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        injectFeature()
        initLayout()
    }

    private fun initLayout() {
        binding.navView.setOnNavigationItemSelectedListener(this)
        onNavigationItemSelected(binding.navView.menu.findItem(R.id.nav_news_feed))
    }

    private fun getCurrentFragment() = supportFragmentManager.currentFragment(R.id.fl_main)

    private fun setNewsFeed() {
        if (getCurrentFragment() !is NewsFeedFragment) {
            supportFragmentManager.replaceFragment(
                R.id.fl_main,
                NewsFeedFragment.newInstance(),
                NewsFeedFragment.TAG
            )
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_news_feed -> setNewsFeed()
        }
        return true
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount

        if (doubleBackToExitPressedOnce) {
            finishAffinity()
            return
        }

        this.doubleBackToExitPressedOnce = true
        "Please click BACK again to exit".showToast(this)

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)

        if (count == 1) {
            finishAffinity()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}