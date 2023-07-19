package com.programmergabut.quranyuk.domain.repository

import com.programmergabut.quranyuk.domain.model.ReadSurah
import com.programmergabut.quranyuk.domain.model.Surah

interface QuranRepository {
    suspend fun getAllSurah(): List<Surah>
    suspend fun getReadSurah(surahId: Int): ReadSurah?
}