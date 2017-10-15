package com.example.yoshidamakoto.constraintlayoutpractice

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import java.io.InputStream

/**
 * Created by yoshidamakoto on 2017/10/15.
 */
@GlideModule
class MyOkhttp3GlideModule : AppGlideModule() {
    override fun registerComponents(context: Context?, glide: Glide?, registry: Registry?) {
        registry?.replace(GlideUrl::class.java, InputStream::class.java,
                OkHttpUrlLoader.Factory(HttpClient.instance))
    }
}