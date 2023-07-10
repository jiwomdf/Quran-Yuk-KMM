package com.programmergabut.quranyuk.android.features.alquran

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.programmergabut.quranyuk.domain.model.Surah
import com.programmergabut.quranyuk.domain.repository.QuranRepositoryImpl
import com.programmergabut.quranyuk.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuranViewModel(
    private val repository: QuranRepositoryImpl,
){
    val allSurah = mutableStateListOf<Surah>()

    fun getAllSurah() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.fetchAllSurah()

            when (response) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    response.data?.let {
                        allSurah.addAll(it)
                        Log.e("rifqi", "surahs: $it")
                    }

                }
                is Resource.Error -> {}
            }
        }

    }
}