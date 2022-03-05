package com.marvel.radwa.data.remote

import com.marvel.radwa.BuildConfig
import com.marvel.radwa.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NoneAuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val authorisedRequestBuilder = originalRequest.newBuilder().apply {
            header(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_VALUE)
            header("Accept", Constants.CONTENT_TYPE_VALUE)
            header("Content-type", "json")
            method(originalRequest.method, originalRequest.body)
        }
        return chain.proceed(authorisedRequestBuilder.build())
    }


}
