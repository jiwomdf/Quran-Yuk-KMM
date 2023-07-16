package com.programmergabut.quranyuk.data.remote.source

import com.programmergabut.quranyuk.data.remote.network.HttpRoutes
import com.programmergabut.quranyuk.data.remote.network.QuranApi
import com.programmergabut.quranyuk.data.remote.response.AllSurahResponse
import com.programmergabut.quranyuk.data.remote.response.ReadSurahEnResponse
import io.ktor.client.call.body
import io.ktor.client.request.get

class RemoteDataSourceImpl(
    quranApi: QuranApi
): RemoteDataSource {
    //http://api.alquran.cloud/v1/juz/30/en.asad

    private val httpClient = quranApi.httpClient

    override suspend fun fetchAllSurah(): AllSurahResponse {
        return httpClient.get(HttpRoutes.All_SURAH).body()
    }

    override suspend fun fetchReadSurahEn(surahId: Int): ReadSurahEnResponse {
        return httpClient.get("${HttpRoutes.READ_SURAH_EN}/$surahId").body()
    }
}