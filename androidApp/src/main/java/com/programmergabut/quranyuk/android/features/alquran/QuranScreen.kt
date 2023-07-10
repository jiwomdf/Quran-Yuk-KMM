package com.programmergabut.quranyuk.android.features.alquran

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.programmergabut.quranyuk.android.MyApplicationTheme
import com.programmergabut.quranyuk.data.remote.network.QuranApi
import com.programmergabut.quranyuk.data.remote.source.RemoteDataSourceImpl
import com.programmergabut.quranyuk.domain.repository.QuranRepositoryImpl

@Composable
fun QuranScreen(
    navController: NavController,
    viewModel: QuranViewModel
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
            QuranViewModel(QuranRepositoryImpl(RemoteDataSourceImpl(QuranApi())))
        )
    }
}
