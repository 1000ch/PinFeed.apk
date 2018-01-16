package com.shogosensui.pinfeed

import android.app.Application
import android.util.Log
import com.shogosensui.pinfeed.payload.*
import com.shogosensui.pinfeed.service.PinboardApiService
import com.shogosensui.pinfeed.service.PinboardFeedService
import com.shogosensui.pinfeed.service.ServiceClientProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainApplication : Application() {
    lateinit var feedClient: PinboardFeedService

    companion object {
        lateinit var preference: Preference
    }

    override fun onCreate() {
        super.onCreate()

        feedClient = ServiceClientProvider.provideFeedService()
        preference = Preference(this)

        feedClient.bookmark(preference.secretToken, "1000ch").enqueue(object : Callback<BookmarkPayload> {
            override fun onResponse(call: Call<BookmarkPayload>, response: Response<BookmarkPayload>) {
                response.body()?.let {
                    Log.d("count", it.count().toString())
                }
            }

            override fun onFailure(call: Call<BookmarkPayload>, t: Throwable?) {
                Log.e("onFailure", "Failed to get bookmark")
            }
        })

        feedClient.network(preference.secretToken, "1000ch").enqueue(object : Callback<BookmarkPayload> {
            override fun onResponse(call: Call<BookmarkPayload>, response: Response<BookmarkPayload>) {
                response.body()?.let {
                    Log.d("count", it.count().toString())
                }
            }

            override fun onFailure(call: Call<BookmarkPayload>, t: Throwable?) {
                Log.e("onFailure", "Failed to get network")
            }
        })
    }
}