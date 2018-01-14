package com.shogosensui.pinfeed.service

import com.shogosensui.pinfeed.payload.BookmarkPayload
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PinboardFeedService {
    companion object {
        val baseUrl: String
            get() = "https://feeds.pinboard.in"

        val secretToken: String
            get() = ""
    }

    @GET("/json/secret:{secretToken}/u:{userId}/network/")
    fun network(
            @Path("secretToken") secretToken: String,
            @Path("userId") userId: String,
            @Query("count") format: Int = 400
    ): Call<BookmarkPayload>

    @GET("/json/secret:{secretToken}/u:{userId}/")
    fun bookmark(
            @Path("secretToken") secretToken: String,
            @Path("userId") userId: String,
            @Query("count") format: Int = 400
    ): Call<BookmarkPayload>
}