package com.shogosensui.pinfeed.service

import com.shogosensui.pinfeed.payload.*
import io.reactivex.Single
import retrofit2.http.*

interface PinboardApiService {
    companion object {
        val baseUrl: String
            get() = "https://api.pinboard.in"
    }

    @GET("/v1/user/api_token")
    fun apiToken(
            @Header("Authorization") authorization: String,
            @Query("format") format: String? = "json"
    ): Single<ApiTokenPayload>

    @GET("/v1/user/secret")
    fun secretToken(
            @Header("Authorization") authorization: String,
            @Query("format") format: String? = "json"
    ): Single<SecretTokenPayload>

    @GET("/v1/posts/get")
    fun getPost(
            @Header("Authorization") authorization: String,
            @Query("format") format: String? = "json",
            @Query("tag") tag: String?,
            @Query("dt") dt: String?,
            @Query("url") url: String?,
            @Query("meta") meta: String?
    ): Single<GetPostPayload>

    @POST("/v1/posts/add")
    fun addPost(
            @Header("Authorization") authorization: String,
            @Query("format") format: String? = "json",
            @Query("url") url: String,
            @Query("description") description: String,
            @Query("extended") extended: String?,
            @Query("tags") tags: String?,
            @Query("dt") dt: String?,
            @Query("replace") replace: String?,
            @Query("shared") shared: String?,
            @Query("toread") toread: String?
    ): Single<AddPostPayload>

    @DELETE("/v1/posts/delete")
    fun deletePost(
            @Header("Authorization") authorization: String,
            @Query("format") format: String? = "json",
            @Query("url") url: String
    ): Single<DeletePostPayload>
}