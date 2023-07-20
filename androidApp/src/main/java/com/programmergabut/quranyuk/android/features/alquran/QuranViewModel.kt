package com.programmergabut.quranyuk.android.features.alquran

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.programmergabut.quranyuk.domain.model.Surah
import com.programmergabut.quranyuk.domain.repository.QuranRepositoryImpl
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


interface IQuranViewModel {
    val allSurah: MutableStateFlow<List<Surah>>
    val searchText: MutableStateFlow<String>
    val isSearching: MutableStateFlow<Boolean>
    val tempSearch: StateFlow<List<Surah>>
    fun getAllSurah()
    fun onSearchTextChange(text: String)
}
class FakeQuranViewModel : IQuranViewModel {
    override val allSurah = MutableStateFlow(emptyList<Surah>())
    override val searchText = MutableStateFlow("")
    override val isSearching = MutableStateFlow(false)
    override val tempSearch = MutableStateFlow(emptyList<Surah>())
    override fun getAllSurah() {}
    override fun onSearchTextChange(text: String) {}

}

class QuranViewModel(
    private val repository: QuranRepositoryImpl,
): IQuranViewModel, ViewModel() {
    override val allSurah = MutableStateFlow(emptyList<Surah>())
    override val searchText = MutableStateFlow("")
    override val isSearching = MutableStateFlow(false)

    @OptIn(FlowPreview::class)
    override val tempSearch = searchText.asStateFlow()
        .debounce(1000L)
        .onEach { isSearching.update { true } }
        .combine(allSurah) { text, surahs ->
            if (text.isBlank()) {
                surahs

            } else {
                delay(2000L)
                surahs.filter {
                    val formattedText = text.lowercase().replace("-", " ")
                    val formattedName = it.englishName.lowercase().replace("-", " ")
                    formattedName.contains(formattedText, true)
                }
            }
        }
        .onEach { isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            allSurah.value
        )

    init {
        getAllSurah()
    }

    override fun getAllSurah() {
        viewModelScope.launch {
            val surahs = repository.getAllSurah()
            allSurah.value = surahs
        }
    }

    override fun onSearchTextChange(text: String) {
        searchText.value = text
    }
}