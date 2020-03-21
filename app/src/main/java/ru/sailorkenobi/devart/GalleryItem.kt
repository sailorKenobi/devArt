package ru.sailorkenobi.devart

import android.os.Parcel
import android.os.Parcelable

data class GalleryItem(val deviationId: String?,
                       val url: String?,
                       val title: String?,
                       val thumbSrc: String?,
                       val previewSrc: String?,
                       val contentSrc: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(deviationId)
        parcel.writeString(url)
        parcel.writeString(title)
        parcel.writeString(thumbSrc)
        parcel.writeString(previewSrc)
        parcel.writeString(contentSrc)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GalleryItem> {
        override fun createFromParcel(parcel: Parcel): GalleryItem {
            return GalleryItem(parcel)
        }

        override fun newArray(size: Int): Array<GalleryItem?> {
            return arrayOfNulls(size)
        }
    }
}