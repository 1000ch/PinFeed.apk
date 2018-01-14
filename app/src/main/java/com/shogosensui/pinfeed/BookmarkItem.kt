package com.shogosensui.pinfeed

import java.net.URL
import java.util.Date

data class BookmarkItem(
        val description: String,
        val extended: String,
        val hash: String,
        val href: URL,
        val meta: String,
        val shared: String,
        val tags: String,
        val time: Date,
        val toread: String)