package com.example.administrator.kweather

import android.app.Application
import com.example.administrator.kweather.extensions.DelegatesExt

/**
 * Created by Administrator on 2016/12/25.
 */
class App : Application() {
    companion object {
        var instance:App by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance=this
    }

}