package com.programmergabut.quranyuk.android

sealed class Screen(val route: String) {
    object QuranScreen: Screen("quran")
}
