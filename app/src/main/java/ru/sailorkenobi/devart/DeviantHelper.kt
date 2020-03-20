package ru.sailorkenobi.devart

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sailorkenobi.devart.model.Result
import ru.sailorkenobi.devart.model.ResultsList
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


fun getUrlString(url: String): String {
    var stringData = ""
    val url = URL(url)
    val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
    try {
        val out = ByteArrayOutputStream()
        val inputStream: InputStream = connection.getInputStream()
        if (connection.getResponseCode() !== HttpURLConnection.HTTP_OK) {
            throw IOException(connection.getResponseMessage().toString() + ": with " + url)
        }
        var bytesRead = 0
        val buffer = ByteArray(1024)
        while (inputStream.read(buffer).also({ bytesRead = it }) > 0) {
            out.write(buffer, 0, bytesRead)
        }
        out.close()
        val byteData = out.toByteArray()
        stringData = byteData.toString()
        Log.d("DeviantHelper", stringData)
    } finally {
        connection.disconnect()
    }
    return stringData
}

fun pollRecentData(recyclerViewAdapter: RecentRecyclerViewAdapter) {
    val getNewestDataService = RetrofitInstance.retrofitInstance!!.create<GetNewestDataService>(
        GetNewestDataService::class.java
    )
    val apiCall = getNewestDataService.get("Bearer $api_token")
    pollAdapterFromApi(recyclerViewAdapter, apiCall)
}

fun pollHotData(recyclerViewAdapter: RecentRecyclerViewAdapter) {
    val getHotDataService = RetrofitInstance.retrofitInstance!!.create<GetHotDataService>(
        GetHotDataService::class.java
    )
    val apiCall = getHotDataService.get("Bearer $api_token")
    pollAdapterFromApi(recyclerViewAdapter, apiCall)
}

fun pollPopularData(recyclerViewAdapter: RecentRecyclerViewAdapter) {
    val getPopularDataService = RetrofitInstance.retrofitInstance!!.create<GetPopularService>(
        GetPopularService::class.java
    )
    val apiCall = getPopularDataService.get("Bearer $api_token")
    pollAdapterFromApi(recyclerViewAdapter, apiCall)
}

fun pollAdapterFromApi(recyclerViewAdapter: RecentRecyclerViewAdapter, call: Call<ResultsList?>?) {
    if (call != null) {
        call.enqueue(object : Callback<ResultsList?> {
            override fun onResponse(
                call: Call<ResultsList?>,
                response: Response<ResultsList?>
            ) {

                val results =  processApiResults(response.body()?.results)
                recyclerViewAdapter.setItems(results)
            }

            override fun onFailure(call: Call<ResultsList?>?, t: Throwable) {
                Log.d("RetroFit", "onFailure")
            }
        })
    }
}

fun processApiResults(results: List<Result>?): List<GalleryItem> {
    val itemsList = mutableListOf<GalleryItem>()
    if (results != null) {
        for (i in 0..results.count() - 1) {
            val deviationid = results[i].deviationid
            val url = results[i].url
            val title = results[i].title
            val preview = results[i].preview?.src
            itemsList.add(GalleryItem(deviationid, url, title, preview))
        }
    }
    return itemsList
}