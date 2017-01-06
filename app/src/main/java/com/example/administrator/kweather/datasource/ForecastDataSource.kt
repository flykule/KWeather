package com.example.administrator.kweather.datasource

import com.example.administrator.kweather.domain.model.ForecastList

/**
 * Created by Administrator on 2017/1/2.
 * 访问数据源统一接口
 */
interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
}