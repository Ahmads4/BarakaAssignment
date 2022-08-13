package com.example.barakaassignment.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.barakaassignment.data.NewsResults
import com.example.barakaassignment.databinding.HorizontalNewsLayoutBinding

class HorizontalNewsAdapter :
    ListAdapter<NewsResults.News, HorizontalNewsAdapter.NewsViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HorizontalNewsAdapter.NewsViewHolder {
        val binding =
            HorizontalNewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HorizontalNewsAdapter.NewsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class NewsViewHolder(val binding: HorizontalNewsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: NewsResults.News) {
            binding.apply {
                Glide.with(itemView)
                    .load(news.urlToImage)
                    .into(newsImage)
                newsTitle.text = news.title
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<NewsResults.News>() {
        override fun areItemsTheSame(
            oldItem: NewsResults.News,
            newItem: NewsResults.News
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: NewsResults.News,
            newItem: NewsResults.News
        ): Boolean {
            return oldItem == newItem
        }
    }
}
