package com.example.barakaassignment.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.barakaassignment.data.NewsResults
import com.example.barakaassignment.data.Stocks
import com.example.barakaassignment.databinding.StockTickerLayoutBinding
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class StocksAdapter :
    ListAdapter<Stocks, StocksAdapter.StocksViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StocksAdapter.StocksViewHolder {
        val binding =
            StockTickerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StocksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StocksAdapter.StocksViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class StocksViewHolder(val binding: StockTickerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val df = DecimalFormat("#.##")
        fun bind(stock: Stocks) {
            binding.apply {
                stockSymbol.text = stock.symbol
                val unformattedPrice = df.format(stock.price.toDouble()).toDouble()
                stockPrice.text = unformattedPrice.toString()
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Stocks>() {
        override fun areItemsTheSame(
            oldItem: Stocks,
            newItem: Stocks
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Stocks,
            newItem: Stocks,
        ): Boolean {
            return oldItem == newItem
        }
    }
}
