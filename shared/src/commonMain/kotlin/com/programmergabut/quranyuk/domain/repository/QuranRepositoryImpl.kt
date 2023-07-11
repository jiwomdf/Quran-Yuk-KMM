package com.programmergabut.quranyuk.domain.repository

import com.programmergabut.quranyuk.data.remote.source.RemoteDataSourceImpl
import com.programmergabut.quranyuk.domain.model.ReadSurahEn
import com.programmergabut.quranyuk.domain.model.Surah
import com.programmergabut.quranyuk.utils.ApiResponse
import com.programmergabut.quranyuk.utils.Resource

class QuranRepositoryImpl(
    private val remote: RemoteDataSourceImpl,
    //private val local: LocalDataSource
): QuranRepository {

    override suspend fun fetchAllSurah(): List<Surah> {
        val data = remote.fetchAllSurah()

        return Surah.mapAllSurah(data)
    }

    override suspend fun fetchReadSurahEn(): List<ReadSurahEn> {
        val data = remote.fetchReadSurahEn()

        return ReadSurahEn.mapReadSurahEn(data)
    }

    /* override suspend fun fetchAllSurah(): List<AllSurahResponse.AllSurah.Ayah> {
        return networkBoundResource(
            query = {
                localDataSource.getAllAgents().map {
                    it.map { it.toAgent() }
                }
            },
            fetch = {
                remoteDataSource.getAllAgents()
            },
            saveFetchResult = {
                val items = it.data.map { it.toAgentEntity() }
                localDataSource.insertAgent(items)
            }
        )
    } */
}