package com.code.refactoring

import android.app.Application

class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        //HttpHelper.instance().init(OkHttpProcessor())
        HttpHelper.instance().init(VolleyPorcess(this))
    }
}