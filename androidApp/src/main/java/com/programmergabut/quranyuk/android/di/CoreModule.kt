package com.programmergabut.quranyuk.android.di

import com.programmergabut.quranyuk.QuranDatabase
import com.programmergabut.quranyuk.data.local.DatabaseDriverFactory
import com.programmergabut.quranyuk.data.local.LocalDataSource
import com.programmergabut.quranyuk.data.local.SqlDelightQuranDataSource
import com.programmergabut.quranyuk.data.remote.network.QuranApi
import com.programmergabut.quranyuk.data.remote.source.RemoteDataSource
import com.programmergabut.quranyuk.data.remote.source.RemoteDataSourceImpl
import com.programmergabut.quranyuk.domain.repository.QuranRepository
import com.programmergabut.quranyuk.domain.repository.QuranRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

val localModule = module {
    single {
        SqlDelightQuranDataSource(QuranDatabase(get()))
    } bind LocalDataSource::class
}

val remoteModule = module {
    single {
        RemoteDataSourceImpl(QuranApi())
    } bind RemoteDataSource::class
}

val driverModule = module {
    single {
        DatabaseDriverFactory(androidContext()).createDriver()
    }
}

val repositoryModule = module {
    single {
        QuranRepositoryImpl(get(), get())
    } bind QuranRepository::class
}