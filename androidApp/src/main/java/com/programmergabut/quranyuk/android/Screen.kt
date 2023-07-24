package com.programmergabut.quranyuk.android

sealed class Screen(val route: String) {
    object QuranScreen: Screen("quran")
    object QuranDetailScreen: Screen("quran_detail_screen")
}

object QuranDetailScreen {
    const val surahId = "surah_id"
}
