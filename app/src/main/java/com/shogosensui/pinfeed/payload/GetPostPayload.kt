package com.shogosensui.pinfeed.payload

import com.shogosensui.pinfeed.BookmarkItem
import java.util.Date

data class GetPostPayload(val date: Date, val user: String, val posts: List<BookmarkItem>)