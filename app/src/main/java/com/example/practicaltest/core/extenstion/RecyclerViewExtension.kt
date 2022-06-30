package com.example.practicaltest.core.extenstion

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setEmptyView(emptyView: TextView, itemCount: Int) = if (itemCount > 0){
    emptyView.makeGone()
    this.makeVisible()
} else {
    emptyView.makeVisible()
    this.makeGone()
}