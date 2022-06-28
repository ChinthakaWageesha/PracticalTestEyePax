package com.example.practicaltest.app.presentation.newsfeed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaltest.R
import com.example.practicaltest.core.general.GoTo

class LatestNewsAdapter : RecyclerView.Adapter<LatestNewsAdapter.LatestNewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestNewsViewHolder {
        return LatestNewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_latest_news, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LatestNewsViewHolder, position: Int) = holder.run {
        onBind(position)
    }

    override fun getItemCount(): Int = 4

    inner class LatestNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val cardLatestNews = itemView.findViewById<CardView>(R.id.card_latest_news)
        private val imgLatestNews = itemView.findViewById<ImageView>(R.id.img_latest_news)
        private val txtPublisher = itemView.findViewById<TextView>(R.id.txt_latest_news_publisher)
        private val txtHeading = itemView.findViewById<TextView>(R.id.txt_latest_news_heading)
        private val txtContent = itemView.findViewById<TextView>(R.id.txt_latest_news_content)

        fun onBind(position: Int) {

            txtPublisher.text = "by Ryan Browne"
            txtHeading.text =
                "Crypto investors should be prepared to lose all their money, BOE governor says"
            txtContent.text =
                "“I’m going to say this very bluntly again,” he added. “Buy them only if you’re prepared to lose all your money."

            cardLatestNews.setOnClickListener { GoTo.latestNews(itemView.context) }
        }
    }

}