package com.example.yoshidamakoto.constraintlayoutpractice

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by yoshidamakoto on 2017/09/28.
 */
object HttpClient {

    val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val instance = OkHttpClient().newBuilder().addInterceptor(logging).build()
}