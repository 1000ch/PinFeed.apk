package com.shogosensui.pinfeed

import android.app.Application
import android.util.Log
import com.shogosensui.pinfeed.payload.Bookmark
import com.shogosensui.pinfeed.api.PinboardFeedApi
import com.shogosensui.pinfeed.api.ApiClientProvider

class MainApplication : Application() {
    lateinit var pinboardFeedApi: PinboardFeedApi

    companion object {
        lateinit var preference: Preference
        var bookmark = listOf<Bookmark>()
        var timeline = listOf<Bookmark>()
    }

    override fun onCreate() {
        super.onCreate()

        pinboardFeedApi = ApiClientProvider.provideFeedApi()
        preference = Preference(this)

        val bookmarkStream = pinboardFeedApi.bookmark(preference.secretToken, "1000ch")
        val timelineStream = pinboardFeedApi.network(preference.secretToken, "1000ch").mergeWith(bookmarkStream)

        bookmarkStream.subscribe({ b ->
            bookmark = b
        }, { error ->
            Log.e("bookmarkStream", "error", error)
        })

        timelineStream.subscribe({ b ->
            timeline = b.sortedBy {
                b -> b.dt
            }
        }, { error ->
            Log.e("timelineStream", "error", error)
        })
    }
}