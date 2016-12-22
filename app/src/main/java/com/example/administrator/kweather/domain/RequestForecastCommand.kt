package com.example.administrator.kweather.domain

import com.example.administrator.kweather.domain.model.ForecastList
import com.example.administrator.kweather.network.ForecastRequest

/**
 * Created by Administrator on 2016/12/9.
 */
class RequestForecastCommand(private val zipCode: String) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(
                forecastRequest.execute()
        )
    }
}