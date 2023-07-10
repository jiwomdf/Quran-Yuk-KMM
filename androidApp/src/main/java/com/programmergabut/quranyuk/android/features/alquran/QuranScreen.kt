package com.programmergabut.quranyuk.android.features.alquran

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.programmergabut.quranyuk.android.MyApplicationTheme

@Composable
fun QuranScreen(
    navController: NavController,
    viewModel: IQuranViewModel
) {
    val context = LocalContext.current

    viewModel.getAllSurah()

    Text(text = "halo")
}

@Preview
@Composable
fun QuranPreview() {
    MyApplicationTheme {
        QuranScreen(
            rememberNavController(),
            FakeQuranViewModel()
        )
    }
}
