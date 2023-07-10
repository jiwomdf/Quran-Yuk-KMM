package com.programmergabut.quranyuk.data.remote.source

import com.programmergabut.quranyuk.data.remote.response.AllSurahResponse
import com.programmergabut.quranyuk.data.remote.response.ReadSurahEnResponse
import com.programmergabut.quranyuk.utils.ApiResponse
import kotlinx.coroutines.Deferred

interface RemoteDataSource {
    suspend fun fetchAllSurahAsync(): Deferred<ApiResponse<AllSurahResponse>>
    suspend fun fetchReadSurahEn(): ApiResponse<ReadSurahEnResponse>
}