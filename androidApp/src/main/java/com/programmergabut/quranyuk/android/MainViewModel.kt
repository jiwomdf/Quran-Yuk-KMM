package com.programmergabut.quranyuk.android

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.programmergabut.quranyuk.data.remote.response.AllSurahResponse
import com.programmergabut.quranyuk.domain.repository.QuranRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: QuranRepository,
){

    val allSurah = mutableStateOf(AllSurahResponse())

    fun getAllSurah() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.fetchAllSurah()

            //TODO JIWO response
            Log.e("jiwo", "getAllSurah: $response", )
            allSurah.value = response
        }
    }
}