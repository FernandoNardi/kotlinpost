package repository

import entity.FullParameters
import entity.HttpResponse
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

abstract class BaseRepository() {
    fun execute(fullParameters: FullParameters): HttpResponse {
        val url = URL("${fullParameters.url}${getQuery(fullParameters.parameters)}")

        val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
        conn.readTimeout = 100000
        conn.connectTimeout = 120000
        conn.requestMethod = fullParameters.method.toString()
        conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded")
        conn.setRequestProperty("charset", "utf-8")
        conn.useCaches = false

        // make the requisition
        conn.connect()

        return if (conn.responseCode == 404) {
            HttpResponse(conn.responseCode, "")
        } else {
            HttpResponse(conn.responseCode, getStringFromInputStream(conn.inputStream))
        }
    }

    private fun getStringFromInputStream(inputStream: InputStream): String {
        return try {
            val strBuilder = StringBuilder()
            val br = BufferedReader(InputStreamReader(inputStream))

            for(line in br.readLines()) {
                strBuilder.append(line)
            }
            strBuilder.toString()
        } catch (exception: Exception) {
            ""
        }
    }

    private fun getQuery(parameters: Map<String, String>): String {
        if (parameters.isEmpty()) {
            return ""
        }

        val result = StringBuilder()
        var first = true

        for (param in parameters) {
            if (first) {
                result.append("?")
                first = false
            } else {
                result.append("&")
            }
            result.append(URLEncoder.encode(param.key, "UTF-8"), "UTF-8")
            result.append("=")
            result.append(URLEncoder.encode(param.value, "UTF-8"))
        }
        return result.toString()
    }
}