package com.shogosensui.pinfeed

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.shogosensui.pinfeed.payload.Bookmark

class BookmarkItemAdapter(val bookmarks: List<Bookmark>) : RecyclerView.Adapter<BookmarkItemViewHolder>() {
    override fun onBindViewHolder(holder: BookmarkItemViewHolder, position: Int) {
        val bookmark = bookmarks[position]

        holder.username.text = bookmark.a
        holder.date.text = bookmark.d.toString()
        holder.comment.text = bookmark.n
        holder.title.text = bookmark.d
    }

    override fun getItemCount(): Int = bookmarks.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkItemViewHolder {
        val bookmarkItemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.bookmark_item, parent, false)
        return BookmarkItemViewHolder(bookmarkItemView)
    }
}