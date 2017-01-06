package com.example.administrator.kweather.datasource

import com.example.administrator.kweather.datasource.server.ServerDataMapper
import com.example.administrator.kweather.db.ForecastDb
import com.example.administrator.kweather.domain.model.ForecastList
import com.example.administrator.kweather.network.ForecastRequest

/**
 * Created by Administrator on 2017/1/2.
 */
class ForecastServer (val dataMapper: ServerDataMapper= ServerDataMapper(),
                      val forecastDb: ForecastDb = ForecastDb()):ForecastDataSource{
    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode,date)
    }

}