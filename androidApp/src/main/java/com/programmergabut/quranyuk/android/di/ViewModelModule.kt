package com.programmergabut.quranyuk.android.di

import com.programmergabut.quranyuk.android.features.alquran.QuranViewModel
import com.programmergabut.quranyuk.android.features.detailquran.QuranDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { QuranViewModel(get()) }
    viewModel { QuranDetailViewModel(get()) }
}