package com.hxh.kt01

import android.app.Application

/**
 * Created by HXH on 2017/5/31 0031.
 * Application
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
    }
}