package ru.sailorkenobi.devart.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResultsList {
    @SerializedName("has_more")
    @Expose
    var hasMore: Boolean? = null

    @SerializedName("next_offset")
    @Expose
    var nextOffset: Int? = null

    @SerializedName("results")
    @Expose
    var results: List<Result>? = null
}