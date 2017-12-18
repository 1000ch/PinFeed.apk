package com.shogosensui.pinfeed

import android.app.Application
import retrofit2.Retrofit
import okhttp3.OkHttpClient

class App : Application() {
    lateinit var apiClient: PinboardService
    lateinit var feedClient: PinboardFeedService

    override fun onCreate() {
        super.onCreate()

        apiClient = Retrofit.Builder()
                .baseUrl("https://api.pinboard.in")
                .build()
                .create(PinboardService::class.java)

        val client = OkHttpClient.Builder()
                .addInterceptor(BasicAuthInterceptor("username", "password"))
                .build()

        feedClient = Retrofit.Builder()
                .baseUrl("https://feeds.pinboard.in")
                .client(client)
                .build()
                .create(PinboardFeedService::class.java)
    }
}