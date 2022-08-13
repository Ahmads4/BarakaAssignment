package com.example.barakaassignment.repository

import com.example.barakaassignment.data.NewsResults
import com.example.barakaassignment.data.Stocks
import com.example.barakaassignment.data.repository.Repository
import javax.inject.Singleton

@Singleton
class FakeRepository : Repository {
    override fun getNews() = testNews
    override suspend fun getStocks(): List<Stocks> {
        TODO("Not yet implemented")
    }
    var testNews: List<NewsResults.News> = listOf(
        NewsResults.News(
            "1",
            "Issy Ronald, CNN",
            "'God needs to come and explain it': How the football world reacted to Real Madrid's extraordinary Champions League semifinal victory",
            "\"We have a score to settle,\" Liverpool star Mo Salah tweeted after Real Madrid staged an extraordinary late comeback against Manchester City to set up a clash with the Reds in the Champions League final on May 28.",
            "https://cdn.cnn.com/cnnnext/dam/assets/220504173124-11-champions-league-semifinal-real-madrid-manchester-city-super-tease.jpg",
            "2022-05-05T10:06:14Z",
        ),
        NewsResults.News(
            "2",
            "Esha Mitra, Rhea Mogul",
            "A 13-year-old girl told the police she had been gang-raped. Then a police officer allegedly raped her",
            "A 13-year-old girl who was allegedly gang-raped by four men in India, was allegedly raped again by a police officer after she tried to seek his help in reporting the initial attack.",
            "https://media.cnn.com/api/v1/images/stellar/prod/220505013803-lalitapur-india-map.jpg?c=16x9&q=w_800,c_fill",
            "2022-05-05T09:58:01Z"
        )

    )
}