package com.programmergabut.quranyuk.android.features.alquran

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.programmergabut.quranyuk.domain.model.Surah
import com.programmergabut.quranyuk.domain.repository.QuranRepositoryImpl
import com.programmergabut.quranyuk.utils.Resource
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
): ViewModel() , IQuranViewModel {
    override val allSurah = mutableStateListOf<Surah>()

    override fun getAllSurah() {
        viewModelScope.launch {
            val response = repository.fetchAllSurah()

            when (response) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    response.data?.let {
                        allSurah.addAll(it)
                        Log.e("rifqi", "surahs: $it")
                    }
                }
                is Resource.Error -> {}
            }
        }

    }
}