package com.programmergabut.quranyuk.data.remote.source

import com.programmergabut.quranyuk.data.remote.response.AllSurahResponse

interface RemoteDataSource {
    suspend fun fetchAllSurah(): AllSurahResponse
}