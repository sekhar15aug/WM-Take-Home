package com.wm.takehome.data.service

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Request interceptor for retrofit calls
 */
object RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        Log.i("WM take home", "Response: $response")
        return response
    }
}