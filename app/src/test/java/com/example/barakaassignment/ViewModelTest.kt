package com.example.barakaassignment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.barakaassignment.repository.FakeRepository
import com.example.barakaassignment.ui.NewsStatus
import com.example.barakaassignment.ui.StocksViewModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ViewModelTest {
    private lateinit var viewModel: StocksViewModel
    var repository = FakeRepository()
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val coroutineRule = MainCoroutineRule()
    @Before
    fun setup() {
        viewModel = StocksViewModel(FakeRepository())
    }
    @Test
    fun `Is livedata properly emitting`() {
        viewModel.verticalNews.observeForever { }
        assertEquals(repository.testNews, viewModel.verticalNews.value)
    }
    @Test
    fun `Insert list, return done status`() {
        viewModel.insertNews(repository.testNews)
        assertEquals(NewsStatus.DONE, viewModel.networkState.value)
    }
}