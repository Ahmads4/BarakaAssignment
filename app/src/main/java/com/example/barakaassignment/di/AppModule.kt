package com.example.barakaassignment.di

import android.content.Context
import com.example.barakaassignment.StocksApplication
import com.example.barakaassignment.data.Stocks
import com.example.barakaassignment.data.csv.CSVParser
import com.example.barakaassignment.data.csv.StockParser
import com.example.barakaassignment.data.repository.Repository
import com.example.barakaassignment.data.repository.RepositoryImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideContext(application: StocksApplication): Context = application.applicationContext
    @Singleton
    @Provides
    fun provideRepository(
        @ApplicationContext context: Context,
        gson: Gson,
        stockParser: StockParser
    ) = RepositoryImpl(context, gson, stockParser) as Repository
    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()
}