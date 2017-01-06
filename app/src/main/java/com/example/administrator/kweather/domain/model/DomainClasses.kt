package com.example.administrator.kweather.domain.model

/**
 * Created by Administrator on 2016/11/30.
 * domain类集合
 */

/**
 * 天气预报集合
 */
data class ForecastList(val id:Long,val city:String,val country:String,
                        val dailyForecastList: List<ModelForecast>){
    operator fun get(position:Int):ModelForecast=dailyForecastList[position]
    fun size():Int=dailyForecastList.size
}
/**
 *天气model
 */
data class ModelForecast(val id: Long,val date:Long,val description:String,
                         val high:Int,val low:Int,val iconUrl:String)