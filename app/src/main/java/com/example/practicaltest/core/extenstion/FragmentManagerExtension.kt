package com.example.practicaltest.core.extenstion

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.practicaltest.R

fun FragmentManager.currentFragment(containerViewId: Int) =
    this.findFragmentById(containerViewId)

fun FragmentManager.replaceFragment(
    containerViewId: Int,
    fragment: Fragment,
    tag: String
) {
    this.beginTransaction()
        .setCustomAnimations(R.animator.enter, R.animator.exit, R.animator.fade_in, R.animator.fade_out)
        .replace(containerViewId, fragment, tag)
        .addToBackStack(null)
        .commit()
}