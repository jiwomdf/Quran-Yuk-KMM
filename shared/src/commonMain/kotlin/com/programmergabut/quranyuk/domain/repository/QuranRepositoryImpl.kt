package com.programmergabut.quranyuk.domain.repository

import com.programmergabut.quranyuk.data.local.LocalDataSource
import com.programmergabut.quranyuk.data.remote.response.readsurah.ReadSurahCombinedResponse
import com.programmergabut.quranyuk.data.remote.source.RemoteDataSource
import com.programmergabut.quranyuk.domain.model.LastRead
import com.programmergabut.quranyuk.domain.model.ReadSurah
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

    override suspend fun getReadSurah(surahId: Int): ReadSurah? {
        return networkBoundResource(
            query = {
                runBlocking {
                    return@runBlocking local.getAyah(surahId)
                }
            },
            fetch = {
                val arResponse = remote.fetchReadSurahAr(surahId)
                val enResponse = remote.fetchReadSurahEn(surahId)
                val combinedResponse = ReadSurahCombinedResponse
                    .mapReadSurahCombinedResponse(arResponse, enResponse)
                combinedResponse
            },
            saveFetchResult = {
                ReadSurah.mapReadSurahEn(it)?.let {
                    local.insertAyah(it)
                }
            },
            shouldFetch = {
                it?.ayah.isNullOrEmpty()
            }
        )
    }

    override suspend fun getLastRead(): LastRead? {
        return runBlocking {
            return@runBlocking local.getLastRead()
        }
    }

    override suspend fun insertLastRead(ayahId: Int, surahId: Int, surahName: String) {
        local.insertLastRead(ayahId,surahId, surahName)
    }

}