/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine;

import android.content.Context;

import com.example.android.sunshine.data.SunshinePreferences;

/**
 * Contains useful utilities for a weather app, such as conversion between Celsius and Fahrenheit,
 * from kph to mph, and from degrees to NSEW.  It also contains the mapping of weather condition
 * codes in OpenWeatherMap to strings.  These strings are contained
 */
public final class SunshineWeatherUtils {
    /**
     * This method will convert a temperature from Celsius to Fahrenheit.
     *
     * @param temperatureInCelsius Temperature in degrees Celsius(°C)
     * @return Temperature in degrees Fahrenheit (°F)
     */
    private static double celsiusToFahrenheit(double temperatureInCelsius) {
        return (temperatureInCelsius * 1.8) + 32;
    }

    /**
     * Temperature data is stored in Celsius by our app. Depending on the user's preference,
     * the app may need to display the temperature in Fahrenheit. This method will perform that
     * temperature conversion if necessary. It will also format the temperature so that no
     * decimal points show. Temperatures will be formatted to the following form: "21°C"
     *
     * @param context     Android Context to access preferences and resources
     * @param temperature Temperature in degrees Celsius (°C)
     * @return Formatted temperature String in the following form:
     * "21°C"
     */
    private static String formatTemperature(Context context, double temperature) {
        int temperatureFormatResourceId = R.string.format_temperature_celsius;

        if (!SunshinePreferences.isMetric(context)) {
            temperature = celsiusToFahrenheit(temperature);
            temperatureFormatResourceId = R.string.format_temperature_fahrenheit;
        }

        /* For presentation, assume the user doesn't care about tenths of a degree. */
        return String.format(context.getString(temperatureFormatResourceId), temperature);
    }

    /**
     * This method will format the temperatures to be displayed in the
     * following form: "HIGH°C / LOW°C"
     *
     * @param context Android Context to access preferences and resources
     * @param high    High temperature for a day in user's preferred units
     * @param low     Low temperature for a day in user's preferred units
     * @return String in the form: "HIGH°C / LOW°C"
     */
    public static String formatHighLows(Context context, double high, double low) {
        long roundedHigh = Math.round(high);
        long roundedLow = Math.round(low);

        String formattedHigh = formatTemperature(context, roundedHigh);
        String formattedLow = formatTemperature(context, roundedLow);

        return formattedHigh + " / " + formattedLow;
    }
}