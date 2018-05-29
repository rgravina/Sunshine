package com.example.android.sunshine

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.example.android.sunshine.network.NetworkHttpReader
import com.example.android.sunshine.network.UrlParser
import com.example.android.sunshine.preferences.SunshinePreferences

class MainActivity : AppCompatActivity() {
    private lateinit var weather: TextView
    private val reader = NetworkHttpReader()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)
        weather = findViewById(R.id.tv_weather_data)

        this.displayForecast()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.forecast, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_refresh) {
            weather.text = null
            displayForecast()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun displayForecast() {
        FetchWeatherTask(reader, { result ->
            weather.text = result
        }).execute(UrlParser.buildUrl("q=${SunshinePreferences.getPreferredWeatherLocation()}"))
    }
}
