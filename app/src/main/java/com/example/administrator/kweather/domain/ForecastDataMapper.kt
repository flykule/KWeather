package com.example.administrator.kweather.domain

import com.example.administrator.kweather.data.Forecast
import com.example.administrator.kweather.data.ForecastResult
import com.example.administrator.kweather.domain.model.ForecastList
import com.example.administrator.kweather.domain.model.ModelForecast
import java.text.DateFormat
import java.util.*

/**
 * Created by Administrator on 2016/11/30.
 * 映射数据到doman类
 */
public class ForecastDataMapper {
    fun convertFromDataModel(forecast: ForecastResult): ForecastList {
        return ForecastList(forecast.city.name,forecast.city.country,
                convertForecastListToDomain(forecast.list)
        )
    }

    private fun convertForecastListToDomain(list: List<Forecast>):
            List<ModelForecast> {
        return list.map { convertForecastItemToDomain(it) }

    }

    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast {
        return ModelForecast(convertDate(forecast.dt),
                forecast.weather[0].description,forecast.main.temp_max.toInt(),
                forecast.main.temp_min.toInt())
    }

    private fun convertDate(date: Long): String {
        val dateInstance = DateFormat.getDateInstance(DateFormat.MEDIUM,
                Locale.getDefault())
        return dateInstance.format(date * 1000)
    }
}