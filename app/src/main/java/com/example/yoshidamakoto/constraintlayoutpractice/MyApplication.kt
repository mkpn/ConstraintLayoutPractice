package com.example.yoshidamakoto.constraintlayoutpractice

import android.app.Application
import timber.log.Timber

/**
 * Created by yoshidamakoto on 2017/09/28.
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}