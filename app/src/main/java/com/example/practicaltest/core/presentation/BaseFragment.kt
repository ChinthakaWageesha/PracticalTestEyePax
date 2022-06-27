package com.example.practicaltest.core.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    private var parentActivity: BaseActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity){
            val activity = context as BaseActivity
            this.parentActivity = activity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated()
    }

    fun showProgress() {
        parentActivity?.showProgress()
    }

    fun hideProgress() {
        parentActivity?.hideProgress()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        hideProgress()
    }

    abstract fun onViewCreated()
}