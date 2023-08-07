package com.example.data.remote

import okhttp3.Interceptor
import okhttp3.Response


//class ErrorInterceptor : Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        var request = chain.request()
//        request = request.newBuilder()
//            .header("X-RapidAPI-Key", "0284a846e8msh795e64f25e783c4p14fffbjsnb9b8ff84841b")
//            .build()
//
//        return chain.proceed(request)
//    }
//}