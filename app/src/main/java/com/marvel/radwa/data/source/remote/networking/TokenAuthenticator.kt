package com.marvel.radwa.data.source.remote.networking

import android.util.Log
import com.marvel.radwa.data.source.local.Session
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(val session: Session) : Authenticator {

    var count = 0

    override fun authenticate(route: Route?, response: Response): Request? {
//        if (session.xAccessToken.isNotEmpty()) {
//            response?.let {
//                Log.e("responsecode", response.code.toString())
//                if (response.code == 401) {
//                    session.logoutLocal()
//                }
//            }
//
//            return response.request.newBuilder().header("Authorization", session.xAccessToken).build()
//        }
//        return null

        return response.request.newBuilder().build()
    }

}