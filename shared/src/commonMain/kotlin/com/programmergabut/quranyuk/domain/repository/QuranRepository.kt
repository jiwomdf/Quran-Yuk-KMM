package com.programmergabut.quranyuk.domain.repository

import com.programmergabut.quranyuk.domain.model.Ayah

interface QuranRepository {
    suspend fun fetchAllSurah(): List<Ayah>
}