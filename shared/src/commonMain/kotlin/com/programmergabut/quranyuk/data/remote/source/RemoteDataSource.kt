package com.programmergabut.quranyuk.data.remote.source

import com.programmergabut.quranyuk.data.remote.network.QuranApi
import com.programmergabut.quranyuk.data.remote.response.SurahResponse
import io.ktor.client.call.body
import io.ktor.client.request.get

class RemoteDataSource(
    private val quranApi: QuranApi
) {
    val httpClient = quranApi.httpClient

    suspend fun getAllAgents(): List<SurahResponse> {
        return httpClient.get("https://valorant-api.com/v1/agents?isPlayableCharacter=true").body()
    }
}