package com.shogosensui.pinfeed

import android.net.Uri
import java.util.*

data class BookmarkItem(
        val title: String,
        val tags: List<String> = listOf<String>(),
        val uri: Uri,
        val date: Date,
        val author: String,
        val description: String)