package com.programmergabut.quranyuk.data.local

import com.programmergabut.quranyuk.domain.model.ReadSurahEn
import com.programmergabut.quranyuk.domain.model.Surah

interface LocalDataSource {
    suspend fun insertSurah(surah: List<Surah>)
    suspend fun getSurah(): List<Surah>

    suspend fun insertAyah(ayah: ReadSurahEn)
    suspend fun getAyah(): ReadSurahEn
}