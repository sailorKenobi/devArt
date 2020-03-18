package ru.sailorkenobi.devart.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result {
    @SerializedName("deviationid")
    @Expose
    var deviationid: String? = null

    @SerializedName("printid")
    @Expose
    var printid: Any? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("category")
    @Expose
    var category: String? = null

    @SerializedName("category_path")
    @Expose
    var categoryPath: String? = null

    @SerializedName("is_favourited")
    @Expose
    var isFavourited: Boolean? = null

    @SerializedName("is_deleted")
    @Expose
    var isDeleted: Boolean? = null

/*    @SerializedName("author")
    @Expose
    var author: Author? = null

    @SerializedName("stats")
    @Expose
    var stats: Stats? = null*/

    @SerializedName("published_time")
    @Expose
    var publishedTime: String? = null

    @SerializedName("allows_comments")
    @Expose
    var allowsComments: Boolean? = null

/*    @SerializedName("preview")
    @Expose
    var preview: Preview? = null

    @SerializedName("content")
    @Expose
    var content: javax.swing.text.AbstractDocument.Content? = null

    @SerializedName("thumbs")
    @Expose
    var thumbs: List<Thumb>? = null*/

    @SerializedName("is_mature")
    @Expose
    var isMature: Boolean? = null

    @SerializedName("is_downloadable")
    @Expose
    var isDownloadable: Boolean? = null

    @SerializedName("download_filesize")
    @Expose
    var downloadFilesize: Int? = null
}