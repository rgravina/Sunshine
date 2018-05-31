package com.example.android.sunshine

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class ForecastAdapter : RecyclerView.Adapter<ForecastAdapterViewHolder>() {
    private var weatherData: Array<String> = arrayOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.forecast_list_item, parent, false)
        return ForecastAdapterViewHolder(view)
    }

    override fun getItemCount(): Int = weatherData.size

    override fun onBindViewHolder(holder: ForecastAdapterViewHolder, position: Int) {
        holder.bind(weatherData[position])
    }

    fun updateWeatherData(weatherData: Array<String>) {
        this.weatherData = weatherData
        notifyDataSetChanged()
    }
}
