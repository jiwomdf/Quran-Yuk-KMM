package com.programmergabut.quranyuk.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.programmergabut.quranyuk.android.features.alquran.QuranScreen
import com.programmergabut.quranyuk.android.features.alquran.QuranViewModel
import com.programmergabut.quranyuk.android.features.detailquran.QuranDetailScreen
import com.programmergabut.quranyuk.android.features.detailquran.QuranDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val quranViewModel by viewModel<QuranViewModel>()
    private val quranDetailViewModel by viewModel<QuranDetailViewModel>()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.QuranScreen.route
                    ) {
                        composable(route = Screen.QuranScreen.route) {
                            QuranScreen(navController, quranViewModel)
                        }
                        with(QuranDetailScreen){
                            composable(
                                route = Screen.QuranDetailScreen.route + "/{$surahId}",
                                arguments = listOf(
                                    navArgument(surahId) {
                                        type = NavType.IntType
                                        defaultValue = 0
                                    }
                                )
                            ) {
                                val surahId = it.arguments?.getInt(surahId, 0) ?: 0
                                QuranDetailScreen(surahId, navController, quranDetailViewModel)
                            }
                        }
                    }
                }
            }
        }
    }
}

