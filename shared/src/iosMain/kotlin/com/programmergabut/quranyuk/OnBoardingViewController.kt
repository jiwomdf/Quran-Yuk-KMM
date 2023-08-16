package com.programmergabut.quranyuk

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.window.ComposeUIViewController
import com.programmergabut.quranyuk.compose.presentation.OnBoardingScreen

fun OnBoardingViewController() = ComposeUIViewController {
    MaterialTheme {
        OnBoardingScreen()
    }
}