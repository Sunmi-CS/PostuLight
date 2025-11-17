package com.tecsup.postulight.arduino

import java.net.HttpURLConnection
import java.net.URL

fun httpGet(urlString: String): String {
    return try {
        val url = URL(urlString)
        val conn = url.openConnection() as HttpURLConnection
        conn.requestMethod = "GET"
        conn.connectTimeout = 5000
        conn.readTimeout = 5000

        val code = conn.responseCode
        if (code == 200) {
            conn.inputStream.bufferedReader().use { it.readText() }
        } else {
            "ERROR: $code"
        }
    } catch (e: Exception) {
        "ERROR: ${e.message}"
    }
}
