package com.example.android.sunshine

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ForecastAdapter(private val listener: ListItemClickListener) : RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder>() {
    private var weatherData: Array<String> = arrayOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.forecast_list_item, parent, false)
        return ForecastAdapterViewHolder(view, listener)
    }

    override fun getItemCount(): Int = weatherData.size

    override fun onBindViewHolder(holder: ForecastAdapterViewHolder, position: Int) {
        holder.bind(weatherData[position])
    }

    fun updateWeatherData(weatherData: Array<String>) {
        this.weatherData = weatherData
        notifyDataSetChanged()
    }

    interface ListItemClickListener {
        fun onListItemClick(clickedItemIndex: Int)
    }

    class ForecastAdapterViewHolder(itemView: View?, private val listener: ListItemClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val weatherTextView: TextView = itemView!!.findViewById(R.id.tv_weather_data)

        init {
            itemView!!.setOnClickListener(this)
        }

        fun bind(weather: String) {
            weatherTextView.text = weather
        }

        override fun onClick(view: View?) {
            listener.onListItemClick(adapterPosition)
        }
    }
}
