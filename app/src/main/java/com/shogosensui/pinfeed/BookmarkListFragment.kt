package com.shogosensui.pinfeed

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class BookmarkListFragment : Fragment() {
    companion object {
        fun getInstance() : BookmarkListFragment {
            return BookmarkListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_bookmark_list, container, false)
    }
}