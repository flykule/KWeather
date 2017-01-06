package com.example.administrator.kweather.db

import com.antonioleiva.weatherapp.extensions.clear
import com.antonioleiva.weatherapp.extensions.parseList
import com.antonioleiva.weatherapp.extensions.parseOpt
import com.example.administrator.kweather.datasource.ForecastDataSource
import com.example.administrator.kweather.domain.model.ForecastList
import com.example.administrator.kweather.extensions.toVarargArray
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.util.*

class ForecastDb :ForecastDataSource{
    val forecastDbHelper:ForecastDbHelper= ForecastDbHelper.instance
    val dataMapper:DbDataMapper= DbDataMapper()


    override fun requestForecastByZipCode(zipCode:Long, date:Long)=forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_ID} = ? " +
                "AND ${DayForecastTable.DATE} >= ?"
        //这里使用了扩展函数来指定范形
        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest,zipCode.toString(),date.toString())
                .parseList { DayForecast(HashMap(it)) }
        val city = select(CityForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                .parseOpt{CityForecast(HashMap(it),dailyForecast)}
//        lambda的最后一行就是它返回的结果
        if (city!=null) dataMapper.convertToDomain(city) else null
    }
    //清楚数据，不需要返回结果，直接返回unit即可
    fun saveForecast(forecastList: ForecastList)= forecastDbHelper.use {
        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)
        //在这里插入结果
        with(dataMapper.convertFromDomain(forecastList)) {
            //map前面的*号代表这个Array会重组为vararg参数，在Java中这是自动进行的，但是在Kotlin中需要显式调用
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach {
                insert(DayForecastTable.NAME, *it.map.toVarargArray())
            }
        }
    }



}