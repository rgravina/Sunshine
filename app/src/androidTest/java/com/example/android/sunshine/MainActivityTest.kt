package com.example.android.sunshine

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.containsString
import org.junit.Assert.assertEquals

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    val rule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun app_containsWeatherList() {
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.example.android.sunshine", appContext.packageName)
        onView(withId(R.id.tv_weather_data))
                .check(matches(withText(containsString("Mountain View"))))
    }
}
