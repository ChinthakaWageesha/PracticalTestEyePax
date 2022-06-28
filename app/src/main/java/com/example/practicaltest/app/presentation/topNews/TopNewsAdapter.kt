package com.example.practicaltest.app.presentation.topNews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaltest.R

class TopNewsAdapter : RecyclerView.Adapter<TopNewsAdapter.TopNewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopNewsViewHolder {
        return TopNewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_top_news, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TopNewsViewHolder, position: Int) = holder.run {
        onBind(position)
    }

    override fun getItemCount(): Int = 10

    inner class TopNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imgTopNews = itemView.findViewById<ImageView>(R.id.img_top_news)
        private val txtPublishDay = itemView.findViewById<TextView>(R.id.txt_top_news_publish_day)
        private val txtHeading = itemView.findViewById<TextView>(R.id.txt_top_news_heading)
        private val txtContent = itemView.findViewById<TextView>(R.id.txt_top_news_content)
        private val txtPublisher = itemView.findViewById<TextView>(R.id.txt_top_news_publisher)

        fun onBind(position: Int) {

            txtPublishDay.text = "Monday, 10 May 2021"
            txtHeading.text =
                "WHO classifies triple-mutant Covid variant from India as global health risk"
            txtContent.text =
                "A World Health Organization official said Monday it is reclassifying " +
                        "the highly contagious triple-mutant Covid variant spreading in India as a “variant " +
                        "of concern,” indicating that it’s become a..."
            txtPublisher.text = "Published by Berkeley Lovelace Jr."

        }
    }

}