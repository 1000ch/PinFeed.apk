package com.shogosensui.pinfeed.api

import com.shogosensui.pinfeed.payload.BookmarkPayload
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PinboardFeedApi {
    companion object {
        val baseUrl: String
            get() = "https://feeds.pinboard.in"
    }

    @GET("/json/secret:{secretToken}/u:{userId}/network/")
    fun network(
            @Path("secretToken") secretToken: String,
            @Path("userId") userId: String,
            @Query("count") format: Int = 400
    ): Single<BookmarkPayload>

    @GET("/json/secret:{secretToken}/u:{userId}/")
    fun bookmark(
            @Path("secretToken") secretToken: String,
            @Path("userId") userId: String,
            @Query("count") format: Int = 400
    ): Single<BookmarkPayload>
}