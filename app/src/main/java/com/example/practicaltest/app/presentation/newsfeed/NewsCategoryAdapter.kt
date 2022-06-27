package com.example.practicaltest.app.presentation.newsfeed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaltest.R

class NewsCategoryAdapter(
    private val items: ArrayList<String>?,
    private val onClickItem: (String) -> Unit
) : RecyclerView.Adapter<NewsCategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_filter_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) = holder.run {
        onBind(position)
    }

    override fun getItemCount(): Int = items?.size ?: 0

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val category = itemView.findViewById<CheckBox>(R.id.cb_selection)

        fun onBind(position: Int) {

            if (position == 0) category.isChecked = true

            category.text = items?.get(position) ?: ""
            category.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    items?.get(position)?.let { onClickItem(it) }
                }
            }
        }
    }

}