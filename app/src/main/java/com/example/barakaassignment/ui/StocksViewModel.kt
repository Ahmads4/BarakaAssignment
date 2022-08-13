package com.example.barakaassignment.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.barakaassignment.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.example.barakaassignment.data.NewsResults
import com.example.barakaassignment.data.Stocks
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

enum class NewsStatus { LOADING, ERROR, DONE }
@HiltViewModel
class StocksViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _horizontalNews = MutableLiveData<List<NewsResults.News>>()
    val horizontalNews: LiveData<List<NewsResults.News>> = _horizontalNews
    private val _verticalNews = MutableLiveData<List<NewsResults.News>>()
    val verticalNews: LiveData<List<NewsResults.News>> = _verticalNews
    private val _networkState = MutableLiveData<NewsStatus>()
    val networkState: LiveData<NewsStatus> = _networkState
    private val _newsList = MutableLiveData<List<NewsResults.News>>()
    val newsList: LiveData<List<NewsResults.News>> = _newsList
    private val _stocksList = MutableLiveData<List<Stocks>>()
    val stocksList: LiveData<List<Stocks>> = _stocksList
    fun insertNews(newsPiece: List<NewsResults.News>) {
        _newsList.value = newsPiece
    }

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            while (isActive) {
                _networkState.value = NewsStatus.LOADING
                try {
                    _networkState.value = NewsStatus.DONE
                    _newsList.value = repository.getNews()
                    _horizontalNews.value = newsList.value!!.take(6)
                    _verticalNews.value = newsList.value!!.takeLast(93)
                    _stocksList.value = repository.getStocks().distinctBy { it.symbol }
                } catch (e: Exception) {
                    _networkState.value = NewsStatus.ERROR
                    _newsList.value = listOf()
                    _horizontalNews.value = listOf()
                    _verticalNews.value = listOf()
                }
                delay(1000)
            }
        }
    }
}