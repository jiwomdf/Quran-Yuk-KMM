package com.programmergabut.quranyuk.android

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.programmergabut.quranyuk.domain.model.Ayah
import com.programmergabut.quranyuk.domain.repository.QuranRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: QuranRepositoryImpl,
){
    val allSurah = mutableStateListOf<Ayah>()

    fun getAllSurah() {
        CoroutineScope(Dispatchers.IO).launch {
            val surahs = repository.fetchAllSurah()
            Log.e("jiwo", "surahs: $surahs")
            allSurah.addAll(surahs)
        }
    }
}