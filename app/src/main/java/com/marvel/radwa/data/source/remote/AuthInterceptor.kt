package com.marvel.radwa.data.source.remote

import com.marvel.radwa.data.source.local.Session
import com.marvel.radwa.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    val session: Session,
) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val authorisedRequestBuilder = originalRequest.newBuilder().apply {
            header("Accept", Constants.CONTENT_TYPE_VALUE)
            header(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_VALUE)
            if (session.xAccessToken.isNotEmpty())
                addHeader(Constants.Authorization, "Bearer " + session.xAccessToken)
            method(originalRequest.method, originalRequest.body)
        }

        return chain.proceed(authorisedRequestBuilder.build())

    }
}