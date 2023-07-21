package com.programmergabut.quranyuk.data.local

import com.programmergabut.quranyuk.domain.model.LastRead
import com.programmergabut.quranyuk.domain.model.ReadSurah
import com.programmergabut.quranyuk.domain.model.Surah

interface LocalDataSource {
    suspend fun insertSurah(surah: List<Surah>)
    suspend fun getSurah(): List<Surah>

    suspend fun insertAyah(readSurah: ReadSurah)
    suspend fun getAyah(surahId: Int): ReadSurah?

    suspend fun getLastRead(): LastRead?
    suspend fun insertLastRead(surahId: Int, ayahId: Int)
}