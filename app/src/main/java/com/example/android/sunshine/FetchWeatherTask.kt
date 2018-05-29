package com.example.android.sunshine

import android.os.AsyncTask
import com.example.android.sunshine.network.HttpReader
import java.net.URL

class FetchWeatherTask(
        private val reader: HttpReader,
        private val preExecute: () -> Unit,
        private val postExecute: (result: String?) -> Unit) : AsyncTask<URL, Void, String>() {
    override fun doInBackground(vararg params: URL?): String? {
        return reader.read(params[0]!!)
    }

    override fun onPreExecute() {
        super.onPreExecute()
        preExecute()
    }
    override fun onPostExecute(result: String?) {
        postExecute(result)
    }
}