package com.example.android.sunshine.network

import java.net.URL

interface HttpReader {
    fun read(url: URL): String?
}