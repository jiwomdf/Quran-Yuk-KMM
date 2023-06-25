package com.programmergabut.quranyuk.data.remote.source

import com.programmergabut.quranyuk.data.remote.network.QuranApi
import com.programmergabut.quranyuk.data.remote.response.AllSurahResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class RemoteDataSource {
    //TODO: http://api.alquran.cloud/v1/juz/30/en.asad

    val httpClient = QuranApi().httpClient

    suspend fun fetchAllSurah(): AllSurahResponse {
        val str = httpClient.get("http://api.alquran.cloud/v1/juz/30/en.asad").bodyAsText()
        print("jiwo $str")
        return httpClient.get("http://api.alquran.cloud/v1/juz/30/en.asad").body()
    }
}