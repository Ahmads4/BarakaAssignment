package com.example.barakaassignment.data.repository

import com.example.barakaassignment.data.NewsResults
import com.example.barakaassignment.data.Stocks

interface Repository {
    fun getNews(): List<NewsResults.News>
    suspend fun getStocks(): List<Stocks>
}