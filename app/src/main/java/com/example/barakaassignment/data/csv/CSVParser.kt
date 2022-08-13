package com.example.barakaassignment.data.csv

import com.example.barakaassignment.data.Stocks
import java.io.InputStream

interface CSVParser<T> {
   suspend fun parse(stream: InputStream): List<T>
}