package com.example.practicaltest.network

import okhttp3.*

class SupportInterceptor : Interceptor, Authenticator {

    private var callback: AuthenticatorCallBack? = null

    override fun intercept(chain: Interceptor.Chain): Response {

        val request: Request.Builder = chain.request().newBuilder()
        return chain.proceed(request.build())

    }

    override fun authenticate(route: Route?, response: Response): Request? {

        return if (response.code != 200) {
            callback?.onUnAuthorizedResponse(response.code)
            null
        } else {
            response.request
                .newBuilder()
                .build()
        }

    }

    fun setAuthCallBackListener(authenticatorCallBack: AuthenticatorCallBack) {
        callback = authenticatorCallBack
    }

    interface AuthenticatorCallBack {
        fun onUnAuthorizedResponse(responseCode: Int)
    }
}