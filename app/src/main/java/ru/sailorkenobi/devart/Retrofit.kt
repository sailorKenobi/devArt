package ru.sailorkenobi.devart

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import ru.sailorkenobi.devart.model.ResultsList


object RetrofitInstance {
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://www.deviantart.com/"

    /**
     * Create an instance of Retrofit object
     */
    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}

interface GetNewestDataService {
    @GET("api/v1/oauth2/browse/newest?limit=30")
    fun get(@Header("Authorization") authorization: String?): Call<ResultsList?>?
}

interface GetHotDataService {
    @GET("api/v1/oauth2/browse/hot?limit=30")
    fun get(@Header("Authorization") authorization: String?): Call<ResultsList?>?
}

interface GetPopularService {
    @GET("api/v1/oauth2/browse/popular?limit=30")
    fun get(@Header("Authorization") authorization: String?): Call<ResultsList?>?
}