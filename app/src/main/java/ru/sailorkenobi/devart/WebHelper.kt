package ru.sailorkenobi.devart

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

suspend fun HttpGet(myURL: String?): String? {
    val result = withContext(Dispatchers.IO)
    {
        val inputStream: InputStream

        // create URL
        val url: URL = URL(myURL)

        // create HttpURLConnection
        val conn: HttpURLConnection = url.openConnection() as HttpURLConnection

        // make GET request to the given URL
        conn.connect()

        // receive response as inputStream
        inputStream = conn.inputStream

        // convert inputstream to string
        if (inputStream != null)
            return@withContext convertInputStreamToString(inputStream)
        else
            return@withContext "Error while making HTTP GET ${myURL}"
    }
    return result
}

private fun convertInputStreamToString(inputStream: InputStream): String {
    val bufferedReader: BufferedReader? = BufferedReader(InputStreamReader(inputStream))
    var line:String? = bufferedReader?.readLine()
    var result:String = ""

    while (line != null) {
        result += line
        line = bufferedReader?.readLine()
    }

    inputStream.close()
    return result
}