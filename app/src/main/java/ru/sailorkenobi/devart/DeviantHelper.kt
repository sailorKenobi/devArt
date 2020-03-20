package ru.sailorkenobi.devart

import android.util.Log
import ru.sailorkenobi.devart.model.Result
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