package com.shogosensui.pinfeed.payload

import java.io.Serializable
import java.net.URL
import java.util.Date

data class GetPostPayload(
        val date: Date,
        val user: String,
        val posts: List<Post>
) : Serializable

data class Post(
        val description: String,
        val extended: String,
        val hash: String,
        val href: URL,
        val meta: String,
        val shared: String,
        val tags: String,
        val time: Date,
        val toread: String
) : Serializable