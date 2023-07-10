package com.programmergabut.quranyuk.data.remote.source

import com.programmergabut.quranyuk.data.remote.network.HttpRoutes
import com.programmergabut.quranyuk.data.remote.network.QuranApi
import com.programmergabut.quranyuk.data.remote.response.AllSurahResponse
import com.programmergabut.quranyuk.data.remote.response.ReadSurahEnResponse
import com.programmergabut.quranyuk.utils.ApiResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class RemoteDataSourceImpl(
    quranApi: QuranApi
): RemoteDataSource {
    //http://api.alquran.cloud/v1/juz/30/en.asad

    private val httpClient = quranApi.httpClient

    override suspend fun fetchAllSurahAsync(): Deferred<ApiResponse<AllSurahResponse>> {
        return CoroutineScope(Dispatchers.Default).async {
            lateinit var response: AllSurahResponse
            try {
                response = httpClient.get(HttpRoutes.All_SURAH).body()
                ApiResponse.Success(response)
            } catch (ex: Exception) {
                ApiResponse.Error(ex)
            }
        }
    }

    override suspend fun fetchReadSurahEn(): ApiResponse<ReadSurahEnResponse> {
        return httpClient.get(HttpRoutes.READ_SURAH_EN).body()
    }
}