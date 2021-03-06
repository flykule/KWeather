package com.example.administrator.kweather.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.administrator.kweather.R
import com.example.administrator.kweather.adapter.ForecastListAdapter
import com.example.administrator.kweather.domain.RequestForecastCommand
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.async
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {
    val url: String = "http://api.openweathermap.org/data/2.5/forecast/city?id=524901&APPID=e1fc8432248f376af65e7d98db631a88"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val forecastList = findViewById(R.id.forecast_list) as RecyclerView
//        val forecastList: RecyclerView = find(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)
//        forecastList.adapter= ForecastListAdapter(items)
        async() {
//            ForecastRequest(url).run()
            val result = RequestForecastCommand("94043").execute()
            Log.d("MainResult", result.city)
            uiThread {
                forecastList.adapter = ForecastListAdapter(result) { toast(it.date) }
                longToast("请求完成")
            }
        }
        Log.d("MainResult", "完成")
    }

    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
            "Sun 6/29 - Sunny - 20/7"
    )

}
