package com.programmergabut.quranyuk.di

import com.programmergabut.quranyuk.QuranDatabase
import com.programmergabut.quranyuk.data.local.DatabaseDriverFactory
import com.programmergabut.quranyuk.data.local.LocalDataSource
import com.programmergabut.quranyuk.data.local.SqlDelightQuranDataSource

class DatabaseModule {
    private val factory by lazy { DatabaseDriverFactory() }
    val noteDataSource: LocalDataSource by lazy {
        SqlDelightQuranDataSource(QuranDatabase(factory.createDriver()))
    }
}