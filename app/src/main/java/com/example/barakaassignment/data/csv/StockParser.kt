package com.example.barakaassignment.data.csv

import com.example.barakaassignment.data.Stocks
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import java.text.DecimalFormat
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class StockParser @Inject constructor() : CSVParser<Stocks> {
    override suspend fun parse(stream: InputStream): List<Stocks> {
        val csvReader = CSVReader(InputStreamReader(stream))
        return withContext(Dispatchers.IO) {
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull { line ->
                    val symbol = line.getOrNull(0)
                    Stocks(
                        symbol = symbol ?: return@mapNotNull null,
                        price = Random.nextDouble(1.11, 99.99).toString()
                    )
                }
                .also {
                    csvReader.close()
                }
        }
    }
}
