package com.programmergabut.quranyuk.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.programmergabut.quranyuk.android.features.alquran.QuranScreen
import com.programmergabut.quranyuk.android.features.alquran.QuranViewModel
import com.programmergabut.quranyuk.data.remote.network.QuranApi
import com.programmergabut.quranyuk.data.remote.source.RemoteDataSourceImpl
import com.programmergabut.quranyuk.domain.repository.QuranRepositoryImpl

class MainActivity : ComponentActivity() {

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
                        composable(
                            route = Screen.QuranScreen.route
                        ) {
                            val viewModel = QuranViewModel(QuranRepositoryImpl(RemoteDataSourceImpl(QuranApi())))
                            QuranScreen(navController, viewModel)
                        }
                    }
                }
            }
        }
    }
}

