package com.programmergabut.quranyuk.android

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.programmergabut.quranyuk.domain.model.Surah
import com.programmergabut.quranyuk.domain.repository.QuranRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: QuranRepositoryImpl,
){
    val allSurah = mutableStateListOf<Surah>()

    fun getAllSurah() {
        CoroutineScope(Dispatchers.IO).launch {
            val surahs = repository.fetchAllSurah()
            Log.e("jiwo", "surahs: $surahs")
            surahs.let {
                allSurah.addAll(it)
            }
        }
    }
}