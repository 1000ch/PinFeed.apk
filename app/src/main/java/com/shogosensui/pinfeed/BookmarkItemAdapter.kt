package com.shogosensui.pinfeed

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class BookmarkItemAdapter(val bookmarkItems: List<BookmarkItem>) : RecyclerView.Adapter<BookmarkItemViewHolder>() {
    override fun onBindViewHolder(holder: BookmarkItemViewHolder, position: Int) {
        val bookmarkItem = bookmarkItems[position]

        holder.username.text = bookmarkItem.author
        holder.date.text = bookmarkItem.date.toString()
        holder.comment.text = bookmarkItem.description
        holder.title.text = bookmarkItem.title
    }

    override fun getItemCount(): Int = bookmarkItems.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkItemViewHolder {
        val bookmarkItemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.bookmark_item, parent, false)
        return BookmarkItemViewHolder(bookmarkItemView)
    }
}