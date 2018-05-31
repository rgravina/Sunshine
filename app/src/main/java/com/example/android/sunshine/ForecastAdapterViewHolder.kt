package com.example.android.sunshine

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView


class ForecastAdapterViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    private val weatherTextView: TextView = itemView!!.findViewById(R.id.tv_weather_data)

    fun bind(weather: String) {
        weatherTextView.text = weather
    }
}
