package com.programmergabut.quranyuk.domain.repository

import com.programmergabut.quranyuk.domain.model.ReadSurahEn
import com.programmergabut.quranyuk.domain.model.Surah

interface QuranRepository {
    suspend fun getAllSurah(): List<Surah>
    suspend fun getReadSurahEn(surahId: Int): ReadSurahEn?
}