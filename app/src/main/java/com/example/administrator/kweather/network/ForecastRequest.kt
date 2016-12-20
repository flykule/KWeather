package com.example.administrator.kweather.network

import android.util.Log
import com.example.administrator.kweather.data.ForecastResult
import com.google.gson.Gson
import java.net.URL

/**
 * Created by Administrator on 2016/11/29.
 * 简单的天气Api网络请求
 */
class ForecastRequest(val zipCode:String){

    companion object {
        private val APP_ID = "e1fc8432248f376af65e7d98db631a88"
        private val APP_URL = "http://api.openweathermap.org/data/2.5/forecast/city?id=524901&APPID="
        private val COMPLETE_URL = "$APP_URL$APP_ID&q="
    }
    fun execute(): ForecastResult {
        val forecastJsonStr = URL(COMPLETE_URL+zipCode).readText()
        Log.d("url", COMPLETE_URL + zipCode)
        Log.d("result", forecastJsonStr)
        return Gson().fromJson(forecastJsonStr,ForecastResult::class.java)
    }
}