package com.shogosensui.pinfeed.payload

import java.io.Serializable
import java.net.URL
import java.util.Date

typealias BookmarkPayload = List<Bookmark>

data class Bookmark(
        val a: String,
        val d: String,
        val dt: Date,
        val n: String,
        val t: List<String>,
        val u: URL
) : Serializable