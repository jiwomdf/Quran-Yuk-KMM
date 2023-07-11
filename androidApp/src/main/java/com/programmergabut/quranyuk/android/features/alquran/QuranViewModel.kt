package com.programmergabut.quranyuk.android.features.alquran

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.programmergabut.quranyuk.domain.model.Surah
import com.programmergabut.quranyuk.domain.repository.QuranRepositoryImpl
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
): IQuranViewModel, ViewModel() {
    override val allSurah = mutableStateListOf<Surah>()

    override fun getAllSurah() {
        viewModelScope.launch {
            val surahs = repository.getAllSurah()
            allSurah.addAll(surahs)
        }
    }
}