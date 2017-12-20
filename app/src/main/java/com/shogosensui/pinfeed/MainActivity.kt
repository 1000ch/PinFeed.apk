package com.shogosensui.pinfeed

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var navigation: BottomNavigationView
    lateinit var timeline: BookmarkListFragment
    lateinit var bookmark: BookmarkListFragment
    lateinit var setting: SettingFragment

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_timeline -> {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.content, timeline)
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_bookmark -> {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.content, bookmark)
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_setting -> {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.content, setting)
                        .commit()
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

        timeline = BookmarkListFragment()
        bookmark = BookmarkListFragment()
        setting = SettingFragment()

        fragmentManager
                .beginTransaction()
                .add(R.id.content, timeline)
                .commit()
    }

}