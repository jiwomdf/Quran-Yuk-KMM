package com.programmergabut.quranyuk.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.programmergabut.quranyuk.Greeting
import com.programmergabut.quranyuk.QuranDatabase
import com.programmergabut.quranyuk.data.local.DatabaseDriverFactory
import com.programmergabut.quranyuk.data.local.SqlDelightQuranDataSource
import com.programmergabut.quranyuk.data.remote.network.QuranApi
import com.programmergabut.quranyuk.data.remote.source.RemoteDataSourceImpl
import com.programmergabut.quranyuk.domain.repository.QuranRepositoryImpl

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val remote = RemoteDataSourceImpl(QuranApi())
                    val driver = DatabaseDriverFactory(applicationContext).createDriver()
                    val local = SqlDelightQuranDataSource(QuranDatabase(driver))
                    val viewModel = MainViewModel(QuranRepositoryImpl(remote, local))
                    viewModel.getAllSurah()

                    GreetingView(Greeting().greet())
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
