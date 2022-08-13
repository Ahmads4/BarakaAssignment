package com.example.barakaassignment.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.barakaassignment.R
import com.example.barakaassignment.data.NewsResults
import com.example.barakaassignment.databinding.ActivityMainBinding
import com.example.barakaassignment.databinding.HorizontalNewsLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<StocksViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val firstAdapter = StocksAdapter()
        val secondAdapter = HorizontalNewsAdapter()
        val thirdAdapter = NewsAdapter()
        with(viewModel) {
            stocksList.observe(this@MainActivity) {
                firstAdapter.submitList(it)
            }
            horizontalNews.observe(this@MainActivity) {
                secondAdapter.submitList(it)
            }
            verticalNews.observe(this@MainActivity) {
                thirdAdapter.submitList(it)
            }
            networkState.observe(this@MainActivity) {
                binding.apply {
                    progressBar.isVisible = it == NewsStatus.LOADING
                    stockRecyclerView.isVisible = it == NewsStatus.DONE
                    horizontalRecyclerView.isVisible = it == NewsStatus.DONE
                    verticalRecyclerView.isVisible = it == NewsStatus.DONE
                }
            }
        }

        binding.apply {
            stockRecyclerView.apply {
                layoutManager =
                    LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = firstAdapter
            }
            horizontalRecyclerView.apply {
                layoutManager =
                    LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = secondAdapter
            }
            verticalRecyclerView.apply {
                layoutManager =
                    LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
                adapter = thirdAdapter
            }
        }
    }
}