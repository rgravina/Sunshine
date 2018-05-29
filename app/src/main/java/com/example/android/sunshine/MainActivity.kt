package com.example.android.sunshine

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.example.android.sunshine.network.NetworkHttpReader
import com.example.android.sunshine.network.UrlParser
import com.example.android.sunshine.preferences.SunshinePreferences

class MainActivity : AppCompatActivity() {
    private lateinit var weather: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var errorMessage: TextView

    private val reader = NetworkHttpReader()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)
        weather = findViewById(R.id.tv_weather_data)
        progressBar = findViewById(R.id.pb_loading_indicator)
        errorMessage = findViewById(R.id.tv_error_message)

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
        FetchWeatherTask(reader, {
            progressBar.visibility = View.VISIBLE
        }, { result ->
            if (result != null) {
                showResults(result)
            } else {
                showErrorMessage()
            }
            progressBar.visibility = View.INVISIBLE
        }).execute(UrlParser.buildUrl("q=${SunshinePreferences.getPreferredWeatherLocation()}"))
    }

    private fun showResults(result: String) {
        weather.text = result
        weather.visibility = View.VISIBLE
        errorMessage.visibility = View.INVISIBLE
    }

    private fun showErrorMessage() {
        weather.text = null
        weather.visibility = View.INVISIBLE
        errorMessage.visibility = View.VISIBLE
    }
}
