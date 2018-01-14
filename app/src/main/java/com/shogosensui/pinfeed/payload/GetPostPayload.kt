package com.shogosensui.pinfeed.payload

import com.shogosensui.pinfeed.BookmarkItem

data class GetPostPayload(val date: String, val user: String, val posts: List<BookmarkItem>)