package com.example.android.sunshine

import android.os.AsyncTask
import com.example.android.sunshine.network.HttpReader
import java.net.URL

class FetchWeatherTask(
        private val reader: HttpReader,
        private val handler: (result: String?) -> Unit) : AsyncTask<URL, Void, String>() {
    override fun doInBackground(vararg params: URL?): String? {
        return reader.read(params[0]!!)
    }

    override fun onPostExecute(result: String?) {
        handler(result)
    }
}