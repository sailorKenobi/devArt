package ru.sailorkenobi.devart.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Content {
    @SerializedName("src")
    @Expose
    var src: String? = null

    @SerializedName("height")
    @Expose
    var height: Int? = null

    @SerializedName("width")
    @Expose
    var width: Int? = null

    @SerializedName("transparency")
    @Expose
    var transparency: Boolean? = null

    @SerializedName("filesize")
    @Expose
    var filesize: Int? = null

}