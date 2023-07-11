package com.programmergabut.quranyuk.android.features.alquran

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.programmergabut.quranyuk.domain.model.Surah
import com.programmergabut.quranyuk.domain.repository.QuranRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


interface IQuranViewModel {
    val allSurah: MutableList<Surah>
    fun getAllSurah()
}
class FakeQuranViewModel : IQuranViewModel {
    override val allSurah = mutableStateListOf<Surah>()
    override fun getAllSurah() {}
}

class QuranViewModel(
    private val repository: QuranRepositoryImpl,
): IQuranViewModel {
    override val allSurah = mutableStateListOf<Surah>()

    override fun getAllSurah() {
        CoroutineScope(Dispatchers.IO).launch {
            val surahs = repository.fetchAllSurah()
            Log.e("jiwo", "surahs: $surahs")
            allSurah.addAll(surahs)
        }
    }
}