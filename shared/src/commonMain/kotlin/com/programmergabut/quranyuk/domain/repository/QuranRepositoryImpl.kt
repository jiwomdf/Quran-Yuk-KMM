package com.programmergabut.quranyuk.domain.repository

import com.programmergabut.quranyuk.data.local.LocalDataSource
import com.programmergabut.quranyuk.data.remote.response.ReadSurahEnResponse
import com.programmergabut.quranyuk.data.remote.source.RemoteDataSource
import com.programmergabut.quranyuk.domain.model.ReadSurahEn
import com.programmergabut.quranyuk.domain.model.ReadSurahEng2
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

    override suspend fun getReadSurahEn(surahId: Int): ReadSurahEng2 {

        val hashMapOfAyah = hashMapOf<Int, ReadSurahEnResponse.ReadSurahEn.Ayah>()
        val arResponse = remote.fetchReadSurahEn(surahId)
        val enResponse = remote.fetchReadSurahAr(surahId)

        for(i in arResponse.data?.ayahs?.indices!!){
            if(hashMapOfAyah[i] == null)
                hashMapOfAyah[i] = arResponse.data?.ayahs!![i]
        }

        for(i in enResponse.data?.ayahs?.indices!!){
            if(hashMapOfAyah[i] != null)
                hashMapOfAyah[i]?.textEn = enResponse.data!!.ayahs?.get(i)?.text
        }

        arResponse.data!!.ayahs = hashMapOfAyah.values.toList()


        return networkBoundResource(
            query = {
                runBlocking {
                    return@runBlocking local.getAyah()
                }
            },
            fetch = {
                arResponse
            },
            saveFetchResult = {
                ReadSurahEn.mapReadSurahEn(it)?.let { it1 -> local.insertAyah(it1) }
            },
            shouldFetch = {
                false
            }
        )
//        val enResponse = remote.fetchReadSurahEn(surahId)
//        val arResponse = remote.fetchReadSurahAr(surahId)
//
//        return ReadSurahEn.mapReadSurahEn(arResponse)
    }

}