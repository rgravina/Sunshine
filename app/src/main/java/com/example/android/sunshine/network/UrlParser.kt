package com.example.android.sunshine.network

import android.net.Uri
import java.net.URL

class UrlParser {
    companion object {
        private const val STATIC_WEATHER_URL = "https://andfun-weather.udacity.com/staticweather"
        private const val PARAM_QUERY = "q"

        fun buildUrl(locationQuery: String): URL {
            val builtUri = Uri.parse(STATIC_WEATHER_URL).buildUpon()
                    .appendQueryParameter(PARAM_QUERY, locationQuery)
                    .build()
            return URL(builtUri.toString())
        }
    }
}