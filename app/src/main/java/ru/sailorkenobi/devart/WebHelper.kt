package ru.sailorkenobi.devart

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
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

suspend fun GetWithToken(myURL: String): String? {
    val token = ""
    val result = withContext(Dispatchers.IO)
    {
        val client: OkHttpClient = OkHttpClient().newBuilder()
            .build()
        val request: Request = Request.Builder()
            .url(myURL)
            .method("GET", null)
            .addHeader("Authorization", "Bearer ${token}")
            .build()
        val response: Response = client.newCall(request).execute()

        if (response.isSuccessful)
            return@withContext response.body?.string()
        else
            return@withContext "Error while making HTTP GET ${myURL} ${response.body?.string()}"
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