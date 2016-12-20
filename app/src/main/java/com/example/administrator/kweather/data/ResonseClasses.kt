package com.example.administrator.kweather.data

/**
 * Created by Administrator on 2016/11/29.
 * 数据类集合
 */
data class ForecastResult(val city: City, val list: List<Forecast>)

data class City(val id: Long, val name: String, val coord: Coordinates,
                val country: String, val population: Int)

data class Coordinates(val lon: Float, val lat: Float)
data class Forecast(val dt: Long, val main:Main,val weather: List<Weather>)
data class Main(val temp:Float,val temp_min:Float,val temp_max:Float,val pressure: Float,val humidity: Int)

data class Temperature(val day: Float, val min: Float, val max: Float,
                       val night: Float, val eve: Float, val morn: Float)

data class Weather(val id: Long, val main: String, val description: String,
                   val icon: String)
