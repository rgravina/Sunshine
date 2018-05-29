package com.example.android.sunshine.network

import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UrlParserTest {
    @Test
    fun buildUrl_constructsQueryUrl() {
        val result = UrlParser.buildUrl("tokyo");
        assertEquals("https://andfun-weather.udacity.com/staticweather?q=tokyo", result.toString())
    }
}
