package com.example.barakaassignment.data

data class Stocks(
    var id: String = generateId(),
    var symbol: String,
    var price: String
)
