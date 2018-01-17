package com.shogosensui.pinfeed

import android.app.Application
import android.util.Log
import com.shogosensui.pinfeed.payload.Bookmark
import com.shogosensui.pinfeed.payload.BookmarkPayload
import com.shogosensui.pinfeed.service.PinboardFeedService
import com.shogosensui.pinfeed.service.ServiceClientProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainApplication : Application() {
    lateinit var feedClient: PinboardFeedService

    companion object {
        lateinit var preference: Preference
        var bookmark = listOf<Bookmark>()
        var timeline = listOf<Bookmark>()
    }

    override fun onCreate() {
        super.onCreate()

        feedClient = ServiceClientProvider.provideFeedService()
        preference = Preference(this)

        val bookmarkStream = feedClient.bookmark(preference.secretToken, "1000ch")
        val timelineStream = feedClient.network(preference.secretToken, "1000ch").mergeWith(bookmarkStream)

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