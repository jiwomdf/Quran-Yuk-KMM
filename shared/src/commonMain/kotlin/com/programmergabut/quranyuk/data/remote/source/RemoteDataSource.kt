package com.programmergabut.quranyuk.data.remote.source

import com.programmergabut.quranyuk.data.remote.response.AllSurahResponse
import com.programmergabut.quranyuk.data.remote.response.ReadSurahEnResponse

interface RemoteDataSource {
    suspend fun fetchAllSurah(): AllSurahResponse
    suspend fun fetchReadSurahEn(): ReadSurahEnResponse
}