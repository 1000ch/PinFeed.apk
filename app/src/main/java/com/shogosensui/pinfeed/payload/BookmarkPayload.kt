package com.shogosensui.pinfeed.payload

import android.os.Parcel
import android.os.Parcelable
import java.net.URL
import java.util.Date

typealias BookmarkPayload = List<Bookmark>

fun Parcel.writeDate(date: Date) {
    writeLong(date.time)
}

fun Parcel.readDate(): Date {
    return Date(readLong())
}

data class Bookmark(
        val a: String,
        val d: String,
        val dt: Date,
        val n: String,
        val t: List<String>,
        val u: URL
) :  Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readDate(),
            parcel.readString(),
            parcel.createStringArrayList(),
            URL(parcel.readString())) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(a)
        parcel.writeString(d)
        parcel.writeDate(dt)
        parcel.writeString(n)
        parcel.writeStringList(t)
        parcel.writeString(u.toString())
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Bookmark> {
        override fun createFromParcel(parcel: Parcel): Bookmark {
            return Bookmark(parcel)
        }

        override fun newArray(size: Int): Array<Bookmark?> {
            return arrayOfNulls(size)
        }
    }
}