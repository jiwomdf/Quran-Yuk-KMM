package com.programmergabut.quranyuk.domain.repository

import com.programmergabut.quranyuk.domain.model.ReadSurahEn
import com.programmergabut.quranyuk.domain.model.Surah
import com.programmergabut.quranyuk.utils.Resource

interface QuranRepository {
    suspend fun fetchAllSurah(): Resource<List<Surah>>
    suspend fun fetchReadSurahEn(): Resource<List<ReadSurahEn>>
}