package com.example.android.sunshine.preferences

class SunshinePreferences {
    companion object {
        private const val DEFAULT_WEATHER_LOCATION = "94043,USA"

        fun getPreferredWeatherLocation(): String {
            return DEFAULT_WEATHER_LOCATION
        }
    }
}