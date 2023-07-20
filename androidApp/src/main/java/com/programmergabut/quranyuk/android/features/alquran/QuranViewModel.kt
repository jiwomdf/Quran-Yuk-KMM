package com.programmergabut.quranyuk.android.features.alquran

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.programmergabut.quranyuk.domain.model.Surah
import com.programmergabut.quranyuk.domain.repository.QuranRepositoryImpl
import kotlinx.coroutines.launch


interface IQuranViewModel {
    val allSurah: List<Surah>
    val tempSearch: SnapshotStateList<Surah>
    fun getAllSurah()
    fun searchSurah(query: String)
}
class FakeQuranViewModel : IQuranViewModel {
    override val allSurah = emptyList<Surah>()
    override val tempSearch = mutableStateListOf<Surah>()
    override fun getAllSurah() {}
    override fun searchSurah(query: String) {}

}

class QuranViewModel(
    private val repository: QuranRepositoryImpl,
): IQuranViewModel, ViewModel() {
    override var allSurah = emptyList<Surah>()
    override val tempSearch = mutableStateListOf<Surah>()

    override fun getAllSurah() {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.getAllSurah()
            }.onSuccess {
                allSurah = it
                tempSearch.addAll(allSurah)
            }.onFailure {

            }
        }
    }

    override fun searchSurah(query: String) {
        tempSearch.clear()
        if(query.isEmpty()) {
            tempSearch.addAll(allSurah)
        } else {
            tempSearch.addAll(allSurah.filter {
                val formattedText = query.lowercase().replace("-", " ")
                val formattedName = it.englishName.lowercase().replace("-", " ")
                formattedName.contains(formattedText, true)
            })
        }
    }
}