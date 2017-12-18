package com.shogosensui.pinfeed

import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PinboardService {
    @GET("/v1/user/api_token")
    fun apiToken(
            @Query("format") format: String = "json"
    ): Call<List<BookmarkItem>>

    @GET("/v1/user/secret")
    fun secretToken(
            @Query("format") format: String = "json"
    ): Call<List<BookmarkItem>>

    @GET("/v1/posts/get")
    fun getPost(
            @Query("format") format: String = "json",
            @Query("tag") tag: String?,
            @Query("dt") dt: String?,
            @Query("url") url: String?,
            @Query("meta") meta: String?
    ): Call<List<BookmarkItem>>

    @POST("/v1/posts/add")
    fun addPost(
            @Query("format") format: String = "json",
            @Query("url") url: String,
            @Query("description") description: String,
            @Query("extended") extended: String?,
            @Query("tags") tags: String?,
            @Query("dt") dt: String?,
            @Query("replace") replace: String?,
            @Query("shared") shared: String?,
            @Query("toread") toread: String?
    ): Call<List<BookmarkItem>>

    @DELETE("/v1/posts/delete")
    fun deletePost(
            @Query("format") format: String = "json",
            @Query("url") url: String
    ): Call<List<BookmarkItem>>
}