package com.programmergabut.quranyuk.android.features.detailquran

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.programmergabut.quranyuk.domain.model.ReadSurah
import com.programmergabut.quranyuk.domain.repository.QuranRepositoryImpl
import kotlinx.coroutines.launch

interface IQuranDetailViewModel {
    val ayahById: MutableState<ReadSurah?>
    fun getAyahId(surahId: Int)
}
class FakeQuranDetailViewModel : IQuranDetailViewModel {
    override val ayahById: MutableState<ReadSurah?> = mutableStateOf(null)
    override fun getAyahId(surahId: Int) {}
}

class QuranDetailViewModel(
    private val repository: QuranRepositoryImpl,
): IQuranDetailViewModel, ViewModel() {
    override val ayahById: MutableState<ReadSurah?> = mutableStateOf(null)

    override fun getAyahId(surahId : Int) {
        viewModelScope.launch {
            val surahs = repository.getReadSurah(surahId)
            ayahById.value = surahs
        }
    }
}