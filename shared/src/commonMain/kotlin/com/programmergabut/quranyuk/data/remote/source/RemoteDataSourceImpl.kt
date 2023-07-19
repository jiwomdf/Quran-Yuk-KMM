package com.programmergabut.quranyuk.data.remote.source

import com.programmergabut.quranyuk.data.remote.network.HttpRoutes
import com.programmergabut.quranyuk.data.remote.network.QuranApi
import com.programmergabut.quranyuk.data.remote.response.allsurah.AllSurahResponse
import com.programmergabut.quranyuk.data.remote.response.readsurah.ReadSurahEnResponse
import io.ktor.client.call.body
import io.ktor.client.request.get

class RemoteDataSourceImpl(
    quranApi: QuranApi
): RemoteDataSource {

    private val httpClient = quranApi.httpClient

    override suspend fun fetchAllSurah(): AllSurahResponse {
        return httpClient.get(HttpRoutes.All_SURAH).body()
    }

    override suspend fun fetchReadSurahEn(surahId: Int): ReadSurahEnResponse {
        return httpClient.get("${HttpRoutes.READ_SURAH}/$surahId/en.asad").body()
    }

    override suspend fun fetchReadSurahAr(surahId: Int): ReadSurahEnResponse {
        return httpClient.get("${HttpRoutes.READ_SURAH}/$surahId").body()
    }
}