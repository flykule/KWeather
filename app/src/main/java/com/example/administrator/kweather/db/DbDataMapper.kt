package com.example.administrator.kweather.db

import com.example.administrator.kweather.domain.model.ForecastList
import com.example.administrator.kweather.domain.model.ModelForecast

/**
 * Created by Administrator on 2017/1/1.
 */
class DbDataMapper {
    fun convertFromDomain(forecastList: ForecastList) = with(forecastList) {
        val daily = dailyForecastList.map { convertDayFromDomain(id, it) }
        CityForecast(id,city,country,daily)
    }

    private fun convertDayFromDomain(cityId: Long, forecast: ModelForecast) = with(forecast) {
        DayForecast(date, description, high, low, iconUrl, cityId)
    }

    fun convertToDomain(forecast: CityForecast) = with(forecast) {
        val daily = dailyForecast.map { convertDayToDomain(it) }
        ForecastList(_id,city,country,daily)
    }

    fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast) {
        ModelForecast(_id, date, description, high, low, iconUrl)
    }
}