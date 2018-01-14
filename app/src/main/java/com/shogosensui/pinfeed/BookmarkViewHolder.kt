package com.shogosensui.pinfeed

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class BookmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val username: TextView = itemView.findViewById(R.id.bookmark_item_username)
    val date: TextView = itemView.findViewById(R.id.bookmark_item_date)
    val comment: TextView = itemView.findViewById(R.id.bookmark_item_comment)
    val title: TextView = itemView.findViewById(R.id.bookmark_item_title)
}