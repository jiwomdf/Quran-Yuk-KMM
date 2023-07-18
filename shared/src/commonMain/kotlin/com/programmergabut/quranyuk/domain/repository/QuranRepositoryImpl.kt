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

    override suspend fun getAllSurah(): List<Surah> {
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
            },
            shouldFetch = {
                it.isEmpty()
            }
        )
    }

    override suspend fun getReadSurahEn(surahId: Int): ReadSurahEn? {
        val enResponse = remote.fetchReadSurahEn(surahId)
        val arResponse = remote.fetchReadSurahAr(surahId)

        return ReadSurahEn.mapReadSurahEn(arResponse)
    }

}