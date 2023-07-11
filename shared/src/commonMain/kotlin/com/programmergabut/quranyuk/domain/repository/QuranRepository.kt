package com.programmergabut.quranyuk.domain.repository

import com.programmergabut.quranyuk.domain.model.ReadSurahEn
import com.programmergabut.quranyuk.domain.model.Surah

interface QuranRepository {
    suspend fun fetchAllSurah(): List<Surah>
    suspend fun fetchReadSurahEn(): List<ReadSurahEn>
}