package com.programmergabut.quranyuk.compose.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingScreen() {
    Scaffold {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Mantap"
        )
    }
}