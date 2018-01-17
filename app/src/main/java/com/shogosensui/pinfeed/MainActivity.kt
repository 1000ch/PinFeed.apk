package com.shogosensui.pinfeed

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.shogosensui.pinfeed.api.ApiClientProvider
import com.shogosensui.pinfeed.payload.Bookmark
import io.reactivex.android.schedulers.AndroidSchedulers

class MainActivity : AppCompatActivity() {
    lateinit var navigation: BottomNavigationView
    lateinit var timelineFragment: BookmarkListFragment
    lateinit var bookmarkFragment: BookmarkListFragment
    lateinit var settingFragment: SettingFragment
    val pinboardFeedApi = ApiClientProvider.provideFeedApi()
    val timeline = mutableListOf<Bookmark>()
    val bookmark = mutableListOf<Bookmark>()

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_timeline -> {
                commitBookmarkListFragment(timelineFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_bookmark -> {
                commitBookmarkListFragment(bookmarkFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_setting -> {
                commitSettingFragment(settingFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        navigation = findViewById<BottomNavigationView>(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        settingFragment = SettingFragment()
        bookmarkFragment = BookmarkListFragment()

        val bookmarkStream = pinboardFeedApi.bookmark(MainApplication.preference.secretToken, "1000ch")
        val timelineStream = pinboardFeedApi.network(MainApplication.preference.secretToken, "1000ch").mergeWith(bookmarkStream)

        bookmarkStream.subscribeOn(AndroidSchedulers.mainThread()).subscribe({ b ->
            bookmark.addAll(b)
            bookmarkFragment = BookmarkListFragment.getInstance(bookmark as ArrayList)
        }, { error ->
            Log.e("bookmarkStream", "error", error)
        })

        timelineStream.subscribeOn(AndroidSchedulers.mainThread()).subscribe({ b ->
            timeline.addAll(b.sortedBy { b -> b.dt })
            timelineFragment = BookmarkListFragment.getInstance(timeline as ArrayList)
            commitBookmarkListFragment(timelineFragment)
        }, { error ->
            Log.e("timelineStream", "error", error)
        })
    }

    private fun commitSettingFragment(fragment: SettingFragment) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.content, fragment)
                .commit()
    }

    private fun commitBookmarkListFragment(fragment: BookmarkListFragment) {
        fragmentManager
                .beginTransaction()
                .add(R.id.content, fragment)
                .commit()
    }
}