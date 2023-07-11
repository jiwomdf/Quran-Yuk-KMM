package com.programmergabut.quranyuk.domain.repository

import com.programmergabut.quranyuk.data.local.LocalDataSource
import com.programmergabut.quranyuk.data.remote.source.RemoteDataSource
import com.programmergabut.quranyuk.domain.model.ReadSurahEn
import com.programmergabut.quranyuk.domain.model.Surah
import com.programmergabut.quranyuk.utils.networkBoundResource
import kotlinx.coroutines.runBlocking

class QuranRepositoryImpl(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource
): QuranRepository {

    override suspend fun fetchAllSurah(): List<Surah> {
        return networkBoundResource(
            query = {
                runBlocking {
                    return@runBlocking local.getSurah()
                }
            },
            fetch = {
                remote.fetchAllSurah()
            },
            saveFetchResult = {
                local.insertSurah(Surah.mapAllSurah(it))
            }
        ) {
            it.isEmpty()
        }
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