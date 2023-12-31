package com.programmergabut.quranyuk.data.remote.source

import com.programmergabut.quranyuk.data.remote.response.allsurah.AllSurahResponse
import com.programmergabut.quranyuk.data.remote.response.readsurah.ReadSurahEnResponse

interface RemoteDataSource {
    suspend fun fetchAllSurah(): AllSurahResponse
    suspend fun fetchReadSurahEn(surahId: Int): ReadSurahEnResponse
    suspend fun fetchReadSurahAr(surahId: Int): ReadSurahEnResponse
}