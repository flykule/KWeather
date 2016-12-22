package com.example.administrator.kweather.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.administrator.kweather.R
import com.example.administrator.kweather.domain.model.ForecastList
import com.example.administrator.kweather.domain.model.ModelForecast
import com.example.administrator.kweather.ui.utils.ctx
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*
/**
 * Created by castle on 16-11-29.
 * recyclerview的适配器
 */
class ForecastListAdapter(val weekForecast: ForecastList,
                          val itemClick: (ModelForecast) -> Unit) :
        RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx)
                .inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view,itemClick)
    }

    override fun getItemCount(): Int =weekForecast.dailyForecastList.size

    class ViewHolder(view: View, val itemClick: (ModelForecast) -> Unit) :RecyclerView.ViewHolder(view){
        fun bindForecast(forecast: ModelForecast){
            with(forecast) {
                Picasso.with(itemView.ctx).load(iconUrl).into(itemView.icon)
                itemView.date.text=date
                itemView.description.text=description
                itemView.maxTemperature.text= "$high"
                itemView.minTemperature.text="$low"
                itemView.setOnClickListener { itemClick(this) }
            }
        }

    }
    interface OnItemClickListener {
        operator fun invoke(forecast: ModelForecast)
    }


}