package com.programmergabut.quranyuk.android.features.detailquran

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.programmergabut.quranyuk.domain.model.ReadSurah
import com.programmergabut.quranyuk.domain.repository.QuranRepositoryImpl
import kotlinx.coroutines.launch

interface IQuranDetailViewModel {
    val readSurah: MutableState<ReadSurah?>
    fun getAyahBySurahId(surahId: Int)
    fun insertLastRead(ayahId: Int, surahId: Int, surahName: String)
}
class FakeQuranDetailViewModel : IQuranDetailViewModel {
    override val readSurah: MutableState<ReadSurah?> = mutableStateOf(null)
    override fun getAyahBySurahId(surahId: Int) {}
    override fun insertLastRead(ayahId: Int, surahId: Int, surahName: String) {}
}

class QuranDetailViewModel(
    private val repository: QuranRepositoryImpl,
): IQuranDetailViewModel, ViewModel() {
    override val readSurah: MutableState<ReadSurah?> = mutableStateOf(null)

    override fun getAyahBySurahId(surahId : Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.getReadSurah(surahId)
            }.onSuccess {
                readSurah.value = it
            }.onFailure {

            }
        }
    }

    override fun insertLastRead(ayahId: Int, surahId: Int, surahName: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.insertLastRead(ayahId, surahId, surahName)
            }.onSuccess {
                Log.e("rifqi", "insertLastRead: sukses nihh")
            }.onFailure {
                Log.e("rifqi", "insertLastRead: $it")
            }
        }
    }
}