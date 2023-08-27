package com.programmergabut.quranyuk.android

import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import com.google.android.gms.tasks.Task
import com.google.android.play.core.integrity.IntegrityManagerFactory
import com.google.android.play.core.integrity.StandardIntegrityManager
import com.google.android.play.core.integrity.StandardIntegrityManager.StandardIntegrityToken
import com.google.android.play.core.integrity.StandardIntegrityManager.StandardIntegrityTokenRequest
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

        callIntegrityApi()
    }

    private fun callIntegrityApi() {
        // Create an instance of a manager.
        val standardIntegrityManager: StandardIntegrityManager =
        IntegrityManagerFactory.createStandard(applicationContext)

        var integrityTokenProvider: StandardIntegrityManager.StandardIntegrityTokenProvider
        val cloudProjectNumber: Long = 100

        // Prepare integrity token. Can be called once in a while to keep internal
        // state fresh.
        standardIntegrityManager.prepareIntegrityToken(
            StandardIntegrityManager.PrepareIntegrityTokenRequest
                .builder()
                .setCloudProjectNumber(cloudProjectNumber)
                .build()
        ).addOnSuccessListener { tokenProvider ->
            integrityTokenProvider = tokenProvider
            callIntegrityServer(integrityTokenProvider)

            //TODO JIWO
            Log.e("jiwo", "callIntegrityApi addOnSuccessListener: $integrityTokenProvider")
        }
        .addOnFailureListener { exception ->
            Toast.makeText(this@MainActivity, exception.message, Toast.LENGTH_SHORT).show()

            //TODO JIWO
            Log.e("jiwo", "callIntegrityApi addOnFailureListener: ${exception.message ?: ""}")
        }
    }

    private fun callIntegrityServer(integrityTokenProvider: StandardIntegrityManager.StandardIntegrityTokenProvider) {

        val requestHash = "2cp24z..." //TODO create the hash
        val integrityTokenResponse: Task<StandardIntegrityToken> = integrityTokenProvider.request(
            StandardIntegrityTokenRequest.builder()
                .setRequestHash(requestHash)
                .build()
        )
        integrityTokenResponse
            .addOnSuccessListener { response: StandardIntegrityToken ->
                //TODO send to server, sendToServer(response.token())

                //TODO JIWO
                Log.e("jiwo", "callIntegrityServer addOnSuccessListener: $response")
            }
            .addOnFailureListener { exception: Exception? ->
                Toast.makeText(this@MainActivity, exception!!.message, Toast.LENGTH_SHORT).show()
                //TODO JIWO
                Log.e("jiwo", "callIntegrityServer addOnFailureListener: ${exception.message ?: ""}")
            }
    }
}

