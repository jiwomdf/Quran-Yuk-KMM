package com.programmergabut.quranyuk.android.features.alquran

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.programmergabut.quranyuk.domain.model.ReadSurahEn
import com.programmergabut.quranyuk.domain.model.ReadSurahEng2
import com.programmergabut.quranyuk.domain.model.Surah
import com.programmergabut.quranyuk.domain.repository.QuranRepositoryImpl
import kotlinx.coroutines.launch


interface IQuranViewModel {
    val allSurah: MutableList<Surah>
    val ayahById: MutableState<ReadSurahEng2>
    fun getAllSurah()

    fun getAyahId()
}
class FakeQuranViewModel : IQuranViewModel {
    override val allSurah = mutableStateListOf<Surah>()
    override val ayahById = mutableStateOf(ReadSurahEng2())
    override fun getAllSurah() {}
    override fun getAyahId() {}
}

class QuranViewModel(
    private val repository: QuranRepositoryImpl,
): IQuranViewModel, ViewModel() {
    override val allSurah = mutableStateListOf<Surah>()
    override val ayahById = mutableStateOf(ReadSurahEng2())


    override fun getAllSurah() {
        viewModelScope.launch {
            val surahs = repository.getAllSurah()
            allSurah.addAll(surahs)
        }
    }

    override fun getAyahId() {
        viewModelScope.launch {
            val surahs = repository.getReadSurahEn(1)
            ayahById.value = surahs

            Log.e("rifqi", "getAyahId: ${ayahById}", )
        }
    }
}