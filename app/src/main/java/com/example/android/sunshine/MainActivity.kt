package com.example.android.sunshine

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.example.android.sunshine.OpenWeatherJsonUtils.getSimpleWeatherStringsFromJson
import com.example.android.sunshine.data.SunshinePreferences.getPreferredWeatherLocation
import com.example.android.sunshine.network.NetworkHttpReader
import com.example.android.sunshine.network.UrlParser

class MainActivity : AppCompatActivity(), ForecastAdapter.ListItemClickListener {
    private lateinit var progressBar: ProgressBar
    private lateinit var errorMessage: TextView
    private lateinit var weatherRecyclerView: RecyclerView

    private val reader = NetworkHttpReader()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)
        progressBar = findViewById(R.id.pb_loading_indicator)
        errorMessage = findViewById(R.id.tv_error_message)
        weatherRecyclerView = findViewById(R.id.rv_forecast)

        val layoutManager = LinearLayoutManager(this)
        weatherRecyclerView.layoutManager = layoutManager
        weatherRecyclerView.setHasFixedSize(true)
        weatherRecyclerView.adapter = ForecastAdapter(this)

        this.displayForecast()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.forecast, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_refresh) {
            displayForecast()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onListItemClick(text: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(Intent.EXTRA_TEXT, text)
        startActivity(intent)
    }

    private fun displayForecast() {
        FetchWeatherTask(reader,
                preExecute = {
                    progressBar.visibility = View.VISIBLE
                },
                postExecute = { results ->
                    if (results != null) {
                        val weatherData = getSimpleWeatherStringsFromJson(this, results)
                        (weatherRecyclerView.adapter as ForecastAdapter).updateWeatherData(weatherData)
                        showResults()
                    } else {
                        showErrorMessage()
                    }
                    progressBar.visibility = View.INVISIBLE
                }
        ).execute(UrlParser.buildUrl("q=${getPreferredWeatherLocation(this)}"))
    }

    private fun showResults() {
        weatherRecyclerView.visibility = View.VISIBLE
        errorMessage.visibility = View.INVISIBLE
    }

    private fun showErrorMessage() {
        weatherRecyclerView.visibility = View.INVISIBLE
        errorMessage.visibility = View.VISIBLE
    }
}
