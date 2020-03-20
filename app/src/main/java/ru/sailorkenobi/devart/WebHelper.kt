package ru.sailorkenobi.devart

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

val api_token = "a0f13d47a29682b2e01f93fed69611e7e9df72490b0a2f0fc1"

suspend fun GetImage(myURL: String?): Bitmap? {
    val result = withContext(Dispatchers.IO)
    {
        val inputStream: InputStream

        val url: URL = URL(myURL)

        val conn: HttpURLConnection = url.openConnection() as HttpURLConnection

        conn.connect()

        Log.d("GetImage", conn.responseMessage)

        inputStream = conn.inputStream

        if (inputStream != null)
            return@withContext  BitmapFactory.decodeStream(inputStream)
        else
            return@withContext null
    }
    return result
}

suspend fun getLatest(): MutableList<GalleryItem> {
    val result = mutableListOf<GalleryItem>()
    try {
        val jsonString = GetWithToken("https://www.deviantart.com/api/v1/oauth2/browse/newest?limit=20")
        Log.d("GetLatest", "Received JSON: $jsonString")
        if (!jsonString.isNullOrBlank()) {
            val fullJsonObject = JSONObject(jsonString)
            val jsonArray = fullJsonObject.getJSONArray("results")
            for (i in 0..jsonArray.length() - 1) {
                var jsonObject = jsonArray.getJSONObject(i)
                val deviationid = jsonObject.getString("deviationid")
                val url = jsonObject.getString("url")
                val title = jsonObject.getString("title")
                result.add(GalleryItem(deviationid, url, title, ""))
            }
        }
    } catch (e: Exception) {
        Log.e("GetLatest", "Failed get items", e)
    }
    return result
}

suspend fun GetWithToken(myURL: String): String? {
    val result = withContext(Dispatchers.IO)
    {
        val client: OkHttpClient = OkHttpClient().newBuilder()
            .build()
        val request: Request = Request.Builder()
            .url(myURL)
            .method("GET", null)
            .addHeader("Authorization", "Bearer $api_token")
            .build()
        val response: Response = client.newCall(request).execute()

        if (response.isSuccessful)
            return@withContext response.body?.string()
        else
            return@withContext "Error while making HTTP GET ${myURL} ${response.body?.string()}"
    }
    return result
}