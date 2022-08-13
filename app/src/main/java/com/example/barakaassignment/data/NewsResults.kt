package com.example.barakaassignment.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

fun generateId(): String {
    return UUID.randomUUID().toString()
}
@Parcelize
data class NewsResults(
    val articles: List<News>,
) : Parcelable {
    @Parcelize
    data class News(
        val id: String = generateId(),
        var author: String,
        var title: String,
        var description: String,
        var urlToImage: String,
        var publishedAt: String,
    ) : Parcelable
}


