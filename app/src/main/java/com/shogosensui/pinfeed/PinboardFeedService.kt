package com.shogosensui.pinfeed

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PinboardFeedService {
    @GET("/json/secret:{secretToken}/u:{userId}/network/")
    fun network(
            @Path("secretToken") secretToken: String,
            @Path("userId") userId: String,
            @Query("count") format: Int = 400
    ): Call<List<BookmarkItem>>

    @GET("/json/secret:{secretToken}/u:{userId}/")
    fun bookmark(
            @Path("secretToken") secretToken: String,
            @Path("userId") userId: String,
            @Query("count") format: Int = 400
    ): Call<List<BookmarkItem>>
}