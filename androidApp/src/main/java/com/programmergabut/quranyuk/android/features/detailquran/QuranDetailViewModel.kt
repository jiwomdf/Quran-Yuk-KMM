package com.programmergabut.quranyuk.android.features.detailquran

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.programmergabut.quranyuk.domain.model.LastRead
import com.programmergabut.quranyuk.domain.model.ReadSurah
import com.programmergabut.quranyuk.domain.repository.QuranRepositoryImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt
import kotlin.random.Random

interface IQuranDetailViewModel {
    val readSurah: MutableState<ReadSurah?>
    val insertLastRead: MutableState<Int>
    val lastRead: MutableState<LastRead?>
    fun getAyahBySurahId(surahId: Int)
    fun insertLastRead(ayahId: Int, surahId: Int, surahName: String)
    fun getLastRead()
}
class FakeQuranDetailViewModel : IQuranDetailViewModel {
    override val readSurah: MutableState<ReadSurah?> = mutableStateOf(null)
    override val insertLastRead: MutableState<Int> = mutableStateOf(0)
    override val lastRead: MutableState<LastRead?> = mutableStateOf(LastRead(1,2, "Test"))

    override fun getAyahBySurahId(surahId: Int) {}
    override fun insertLastRead(ayahId: Int, surahId: Int, surahName: String) {}
    override fun getLastRead() {}
}

class QuranDetailViewModel(
    private val repository: QuranRepositoryImpl,
): IQuranDetailViewModel, ViewModel() {
    override val readSurah: MutableState<ReadSurah?> = mutableStateOf(null)
    override val insertLastRead: MutableState<Int> = mutableStateOf(0)
    override val lastRead: MutableState<LastRead?> = mutableStateOf(null)

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
                insertLastRead.value = Random.nextInt()
            }.onFailure {}
        }
    }

    override fun getLastRead() {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.getLastRead()
            }.onSuccess {
                lastRead.value = it
            }.onFailure {
            }
        }
    }
}