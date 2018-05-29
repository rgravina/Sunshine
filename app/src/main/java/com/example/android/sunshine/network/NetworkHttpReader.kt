package com.example.android.sunshine.network

import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class NetworkHttpReader : HttpReader {
    override fun read(url: URL): String? {
        val urlConnection = url.openConnection() as HttpURLConnection
        try {
            val inputStream = urlConnection.inputStream
            val scanner = Scanner(inputStream)
            scanner.useDelimiter("\\A")
            val hasInput = scanner.hasNext()
            return if (hasInput) {
                scanner.next()
            } else {
                null
            }
        } finally {
            urlConnection.disconnect()
        }
    }
}