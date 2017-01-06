package com.example.administrator.kweather.datasource.server

import com.example.administrator.kweather.data.Forecast
import com.example.administrator.kweather.data.ForecastResult
import com.example.administrator.kweather.domain.model.ForecastList
import com.example.administrator.kweather.domain.model.ModelForecast
import java.text.DateFormat
import java.util.*

/**
 * Created by Administrator on 2017/1/2.
 */
class ServerDataMapper {
    fun convertToDomain(zipCode:Long,forecast: ForecastResult): ForecastList {
        return ForecastList(zipCode,forecast.city.name,forecast.city.country,
                convertForecastListToDomain(forecast.list)
        )
    }

    private fun convertForecastListToDomain(list: List<Forecast>):
            List<ModelForecast> {
        return list.map { convertForecastItemToDomain(it) }
    }
    private fun generateIconUrl(iconCode:String):String
            ="http://openweathermap.org/img/w/$iconCode.png"
    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast {
        return ModelForecast(-1,forecast.dt*1000,
                forecast.weather[0].description,forecast.main.temp_max.toInt(),
                forecast.main.temp_min.toInt(),generateIconUrl(forecast.weather[0].icon))
    }

    private fun convertDate(date: Long): String {
        val dateInstance = DateFormat.getDateInstance(DateFormat.MEDIUM,
                Locale.getDefault())
        return dateInstance.format(date * 1000)
    }
}