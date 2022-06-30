package com.example.practicaltest.app.presentation.topNews

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaltest.R
import com.example.practicaltest.app.domain.model.DArticle
import com.example.practicaltest.core.extenstion.loadImage

class TopNewsAdapter(
    private val topNewsList: MutableList<DArticle>?
) : RecyclerView.Adapter<TopNewsAdapter.TopNewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopNewsViewHolder {
        return TopNewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_top_news, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TopNewsViewHolder, position: Int) = holder.run {
        onBind(position)
    }

    override fun getItemCount(): Int = topNewsList?.size ?: 0

    inner class TopNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imgTopNews = itemView.findViewById<ImageView>(R.id.img_top_news)
        private val txtPublishDay = itemView.findViewById<TextView>(R.id.txt_top_news_publish_day)
        private val txtHeading = itemView.findViewById<TextView>(R.id.txt_top_news_heading)
        private val txtContent = itemView.findViewById<TextView>(R.id.txt_top_news_content)
        private val txtPublisher = itemView.findViewById<TextView>(R.id.txt_top_news_publisher)

        @SuppressLint("SetTextI18n")
        fun onBind(position: Int) {

            val topNews = topNewsList?.get(position)

            txtPublishDay.text = topNews?.publishedAt!!.split("T")[0]
            txtHeading.text = topNews.title
            txtContent.text = topNews.content
            txtPublisher.text = "Published by ${topNews.author}"
            imgTopNews.loadImage(topNews.urlToImage)

        }
    }

}