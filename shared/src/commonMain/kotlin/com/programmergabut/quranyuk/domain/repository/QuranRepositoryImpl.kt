package com.programmergabut.quranyuk.domain.repository

import com.programmergabut.quranyuk.data.remote.source.RemoteDataSourceImpl
import com.programmergabut.quranyuk.domain.model.Ayah

class QuranRepositoryImpl(
    private val remote: RemoteDataSourceImpl,
    //private val local: LocalDataSource
): QuranRepository {

    override suspend fun fetchAllSurah(): List<Ayah> {
        val data = remote.fetchAllSurah()

        return Ayah.mapAyah(data)
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