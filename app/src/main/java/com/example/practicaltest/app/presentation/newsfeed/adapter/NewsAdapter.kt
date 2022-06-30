package com.example.practicaltest.app.presentation.newsfeed.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaltest.R
import com.example.practicaltest.app.domain.model.DArticle
import com.example.practicaltest.core.extenstion.loadImage

class NewsAdapter(
    private val newsList: MutableList<DArticle>?
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) = holder.run {
        onBind(position)
    }

    override fun getItemCount(): Int = newsList?.size ?: 0

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imgNews = itemView.findViewById<ImageView>(R.id.img_news)
        private val txtPublisher = itemView.findViewById<TextView>(R.id.txt_news_publisher)
        private val txtHeading = itemView.findViewById<TextView>(R.id.txt_news_heading)
        private val txtPublishDay = itemView.findViewById<TextView>(R.id.txt_news_publish_day)

        fun onBind(position: Int) {

            val news = newsList?.get(position)

            txtPublisher.text = news?.author
            txtPublishDay.text = news?.publishedAt!!.split("T")[0]
            txtHeading.text = news.title
            imgNews.loadImage(news.urlToImage)
        }
    }
}