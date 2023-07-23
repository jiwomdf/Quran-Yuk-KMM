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
    val ayahById: MutableState<ReadSurah?>
    fun getAyahId(surahId: Int)
    fun insertLastRead(surahId: Int, AyahId: Int, surahName:String)
}
class FakeQuranDetailViewModel : IQuranDetailViewModel {
    override val ayahById: MutableState<ReadSurah?> = mutableStateOf(null)
    override fun getAyahId(surahId: Int) {}
    override fun insertLastRead(surahId: Int, AyahId: Int, surahName: String) {}
}

class QuranDetailViewModel(
    private val repository: QuranRepositoryImpl,
): IQuranDetailViewModel, ViewModel() {
    override val ayahById: MutableState<ReadSurah?> = mutableStateOf(null)

    override fun getAyahId(surahId : Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.getReadSurah(surahId)
            }.onSuccess {
                ayahById.value = it
            }.onFailure {

            }
        }
    }

    override fun insertLastRead(surahId: Int, ayahId: Int, surahName: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.insertLastRead(surahId, ayahId, surahName)
            }.onSuccess {
                Log.e("rifqi", "insertLastRead: sukses nihh", )
            }.onFailure {

            }
        }
    }
}