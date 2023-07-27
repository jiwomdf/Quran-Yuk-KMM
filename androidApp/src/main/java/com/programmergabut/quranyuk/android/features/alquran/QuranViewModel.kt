package com.programmergabut.quranyuk.android.features.alquran

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.programmergabut.quranyuk.domain.model.LastRead
import com.programmergabut.quranyuk.domain.model.Surah
import com.programmergabut.quranyuk.domain.repository.QuranRepositoryImpl
import kotlinx.coroutines.launch


interface IQuranViewModel {
    val tempSearch: MutableState<List<Surah>>
    val lastRead: MutableState<LastRead?>
    fun getAllSurah()
    fun searchSurah(query: String)
    fun getLastRead()
}
class FakeQuranViewModel : IQuranViewModel {
    override val tempSearch = mutableStateOf<List<Surah>>(emptyList())
    override val lastRead: MutableState<LastRead?> = mutableStateOf(LastRead(1,2, "Test"))
    override fun getAllSurah() {}
    override fun searchSurah(query: String) {}
    override fun getLastRead() {}
}

class QuranViewModel(
    private val repository: QuranRepositoryImpl
): IQuranViewModel, ViewModel() {
    private var allSurah = emptyList<Surah>()
    override var tempSearch = mutableStateOf<List<Surah>>(emptyList())
    override var lastRead = mutableStateOf<LastRead?>(null)

    override fun getAllSurah() {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.getAllSurah()
            }.onSuccess {
                allSurah = it
                tempSearch.value = it
            }.onFailure {}
        }
    }

    override fun searchSurah(query: String) {
        val result = if(query.isEmpty()) {
            allSurah
        } else {
            allSurah.filter {
                val formattedText = query.lowercase().replace("-", " ")
                val formattedName = it.englishName.lowercase().replace("-", " ")
                formattedName.contains(formattedText, true)
            }
        }
        tempSearch.value = result
    }

    override fun getLastRead() {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.getLastRead()
            }.onSuccess {
                lastRead.value = it
            }.onFailure {}
        }
    }

}