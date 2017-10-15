package com.example.yoshidamakoto.constraintlayoutpractice

import com.google.gson.Gson

/**
 * Created by yoshidamakoto on 2017/10/15.
 */
inline fun <reified T> Gson.fromJson(json: String): T {
    return fromJson(json, T::class.java)
}
