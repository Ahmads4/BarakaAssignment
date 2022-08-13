package com.example.barakaassignment.data.repository

import android.content.Context
import com.example.barakaassignment.data.NewsResults
import com.example.barakaassignment.data.Stocks
import com.example.barakaassignment.data.csv.CSVParser
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONArray
import org.json.JSONObject
import java.nio.charset.Charset
import javax.inject.Inject
import javax.inject.Singleton
import com.google.gson.Gson

@Singleton
class RepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson,
    val stocksParser: CSVParser<Stocks>
) : Repository {
    override fun getNews(): List<NewsResults.News> {
        val inputStream = context.assets.open("news.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val jsonString = buffer.toString(Charset.defaultCharset())
        val items = parseJsonString(jsonString!!)

        return items
    }

    override suspend fun getStocks(): List<Stocks> {
        return stocksParser.parse(context.assets.open("stocks.csv"))
    }

    private fun parseJsonString(jsonString: String): List<NewsResults.News> {
        val mainObject = JSONObject(jsonString)
        val newsItems = mutableListOf<NewsResults.News>()
        val newsArray = mainObject.getJSONArray("articles")
        newsArray.forEach { newsObject ->
            val newsItem =
                gson.fromJson(newsObject.toString(), NewsResults.News::class.java)
            newsItems.add(newsItem)
        }

        return newsItems
    }
}

fun JSONArray.forEach(jsonObject: (JSONObject) -> Unit) {
    for (index in 0 until this.length()) jsonObject(this[index] as JSONObject)
}

