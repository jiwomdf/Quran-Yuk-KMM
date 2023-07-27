package com.programmergabut.quranyuk.domain.repository

import com.programmergabut.quranyuk.data.local.LocalDataSource
import com.programmergabut.quranyuk.data.remote.response.readsurah.ReadSurahCombinedResponse
import com.programmergabut.quranyuk.data.remote.source.RemoteDataSource
import com.programmergabut.quranyuk.domain.model.ReadSurah
import com.programmergabut.quranyuk.util.allSurah
import com.programmergabut.quranyuk.util.allSurahResponse
import com.programmergabut.quranyuk.util.lastRead
import com.programmergabut.quranyuk.util.readSurah
import com.programmergabut.quranyuk.util.readSurahArResponse
import com.programmergabut.quranyuk.util.readSurahEnResponse
import com.programmergabut.quranyuk.util.surahId
import kotlinx.coroutines.runBlocking
import org.mockito.Mockito
import org.mockito.Mockito.atLeast
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.`when`
import kotlin.test.BeforeTest
import kotlin.test.Test

internal class QuranRepositoryTest {

    private lateinit var quranRepository: QuranRepository
    private var remote = mock(RemoteDataSource::class.java)
    private var local = mock(LocalDataSource::class.java)

    @BeforeTest
    fun setup() {
        quranRepository = QuranRepositoryImpl(remote, local)
    }

    @Test
    fun `getAllSurah empty surah in db, has all interaction`(): Unit = runBlocking {
        `when`(local.getSurah()).thenReturn(emptyList())
        `when`(local.insertSurah(allSurah)).thenReturn(Unit)
        `when`(remote.fetchAllSurah()).thenReturn(allSurahResponse)

        quranRepository.getAllSurah()

        Mockito.verify(local, atLeast(2)).getSurah()
        Mockito.verify(local).insertSurah(allSurah)
        Mockito.verify(remote).fetchAllSurah()
    }

    @Test
    fun `getAllSurah has surahs in db, just has getSurah() interaction`(): Unit = runBlocking {
        `when`(local.getSurah()).thenReturn(allSurah)
        `when`(local.insertSurah(allSurah)).thenReturn(Unit)
        `when`(remote.fetchAllSurah()).thenReturn(allSurahResponse)

        quranRepository.getAllSurah()

        Mockito.verify(local, atLeast(2)).getSurah()
        Mockito.verify(local, never()).insertSurah(allSurah)
        Mockito.verify(remote, never()).fetchAllSurah()
    }

    @Test
    fun `getReadSurah empty surah in db, has all interaction`() : Unit = runBlocking {
        `when`(local.getAyah(surahId)).thenReturn(null)
        `when`(local.insertAyah(readSurah)).thenReturn(Unit)
        `when`(remote.fetchReadSurahAr(surahId)).thenReturn(readSurahArResponse)
        `when`(remote.fetchReadSurahEn(surahId)).thenReturn(readSurahEnResponse)

        quranRepository.getReadSurah(surahId)

        Mockito.verify(local, atLeast(2)).getAyah(surahId)

        val result = ReadSurahCombinedResponse
            .mapReadSurahCombinedResponse(readSurahArResponse, readSurahEnResponse)

        ReadSurah.mapReadSurahEn(result)?.let { Mockito.verify(local).insertAyah(it) }
        Mockito.verify(remote).fetchReadSurahAr(surahId)
        Mockito.verify(remote).fetchReadSurahEn(surahId)
    }

    @Test
    fun `getReadSurah has surahs in db, just has getSurah() interaction`() : Unit = runBlocking {
        `when`(local.getAyah(surahId)).thenReturn(readSurah)
        `when`(local.insertAyah(readSurah)).thenReturn(Unit)
        `when`(remote.fetchReadSurahAr(surahId)).thenReturn(readSurahEnResponse)
        `when`(remote.fetchReadSurahEn(surahId)).thenReturn(readSurahEnResponse)

        quranRepository.getReadSurah(surahId)

        Mockito.verify(local, atLeast(2)).getAyah(surahId)
        Mockito.verify(local, never()).insertAyah(readSurah)
        Mockito.verify(remote, never()).fetchReadSurahAr(surahId)
        Mockito.verify(remote, never()).fetchReadSurahEn(surahId)
    }

    @Test
    fun `getLastRead, local getLastRead called`() : Unit = runBlocking {
        `when`(local.getLastRead()).thenReturn(lastRead)
        quranRepository.getLastRead()
        Mockito.verify(local).getLastRead()
    }

    @Test
    fun insertLastRead() : Unit = runBlocking {
        `when`(local.insertLastRead(lastRead.ayahId, lastRead.surahId, lastRead.surahName)).thenReturn(Unit)
        quranRepository.insertLastRead(lastRead.ayahId, lastRead.surahId, lastRead.surahName)
        Mockito.verify(local).insertLastRead(lastRead.ayahId, lastRead.surahId, lastRead.surahName)
    }
}