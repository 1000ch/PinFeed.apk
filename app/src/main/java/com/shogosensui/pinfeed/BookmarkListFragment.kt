package com.shogosensui.pinfeed

import android.app.Fragment
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*

class BookmarkListFragment : Fragment() {
    lateinit var recyclerView: RecyclerView

    companion object {
        fun getInstance(): BookmarkListFragment {
            return BookmarkListFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.bookmark_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view as RecyclerView
        val bookmarkItems = mutableListOf<BookmarkItem>()
        bookmarkItems.add(BookmarkItem("aaa", listOf(), Uri.parse("https://google.co.jp"), Date("2017/12/18"), "1000ch", "テスト"))
        recyclerView.adapter = BookmarkItemAdapter(bookmarkItems)
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }
}