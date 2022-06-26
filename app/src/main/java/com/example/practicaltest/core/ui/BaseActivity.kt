package com.example.practicaltest.core.ui

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Window
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.practicaltest.R
import com.example.practicaltest.core.extenstion.showToast
import com.example.practicaltest.core.util.ApiResponseCodes
import com.example.practicaltest.core.util.Msg
import com.example.practicaltest.network.SupportInterceptor
import org.koin.android.ext.android.inject

abstract class BaseActivity : AppCompatActivity() {

    abstract val toolBarTitle: String
    private var progress: Dialog? = null
    private val mNetworkInterceptor by inject<SupportInterceptor>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setProgressLoader()
        setNetworkInterceptor()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        initToolbar()
    }

    private fun initToolbar() {
        findViewById<TextView>(R.id.txt_toolbar_title)?.text = toolBarTitle
        findViewById<ImageButton>(R.id.btn_toolbar_back)?.setOnClickListener { onBackPressed() }
    }

    private fun setProgressLoader() {
        progress = Dialog(this, R.style.ProgressbarStyle).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.item_progress_loader)
            setCancelable(true)
            setCanceledOnTouchOutside(true)
        }
    }

    private fun setNetworkInterceptor() {
        mNetworkInterceptor.setAuthCallBackListener(object :
            SupportInterceptor.AuthenticatorCallBack {

            override fun onUnAuthorizedResponse(responseCode: Int) {
                when (responseCode) {
                    ApiResponseCodes.UNAUTHORIZED -> handleResponse()
                    ApiResponseCodes.SERVER_ERROR -> runOnUiThread {
                        Msg.ERROR_COMMON.showToast(this@BaseActivity)
                    }
                }
            }
        })
    }

    private fun handleResponse() {
        val intent = Intent(this@BaseActivity, CoreActivity::class.java)
        startActivity(intent)
        finishAffinity()

    }

    fun showProgress() {
        progress?.let {
            if (!it.isShowing) {
                it.show()
            }
        }
    }

    fun hideProgress() {
        progress?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        hideProgress()
    }
}