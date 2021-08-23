package com.moneytransfer.demo

import android.app.Application
import android.content.Context

class DemoApplication : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: DemoApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }
}