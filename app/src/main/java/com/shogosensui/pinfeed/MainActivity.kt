package com.shogosensui.pinfeed

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var content: FrameLayout
    lateinit var navigation: BottomNavigationView
    lateinit var timeline: TextView
    lateinit var bookmark: TextView
    lateinit var setting: TextView

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_timeline -> {
                content.removeAllViews()
                content.addView(timeline)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_bookmark -> {
                content.removeAllViews()
                content.addView(bookmark)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_setting -> {
                content.removeAllViews()
                content.addView(setting)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        content = findViewById<FrameLayout>(R.id.content)
        navigation = findViewById<BottomNavigationView>(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        timeline = TextView(this)
        timeline.text = "Timeline"
        bookmark = TextView(this)
        bookmark.text = "Bookmark"
        setting = TextView(this)
        setting.text = "Setting"

        content.addView(timeline)
    }

}