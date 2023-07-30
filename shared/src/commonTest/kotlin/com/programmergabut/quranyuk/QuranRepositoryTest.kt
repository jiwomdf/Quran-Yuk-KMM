package com.programmergabut.quranyuk

import com.programmergabut.quranyuk.data.local.LocalDataSource
import com.programmergabut.quranyuk.data.remote.source.RemoteDataSource
import com.programmergabut.quranyuk.domain.repository.QuranRepository
import com.programmergabut.quranyuk.domain.repository.QuranRepositoryImpl
import com.programmergabut.quranyuk.util.allSurah
import com.programmergabut.quranyuk.util.allSurahResponse
import io.mockative.Mock
import io.mockative.Times
import io.mockative.classOf
import io.mockative.given
import io.mockative.mock
import io.mockative.verify
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

internal class QuranRepositoryTest {

    private lateinit var quranRepository: QuranRepository

    @Mock
    private var remote = mock(classOf<RemoteDataSource>())
    @Mock
    private var local = mock(classOf<LocalDataSource>())

    @BeforeTest
    fun setup() {
        quranRepository = QuranRepositoryImpl(remote, local)
    }

    @Test
    fun `getAllSurah empty surah in db_ has all interaction`(): Unit = runBlocking {
        given(local).coroutine { getSurah() }.then { emptyList() }
        given(local).coroutine { insertSurah(allSurah) }.then { }
        given(remote).coroutine { fetchAllSurah() }.then { allSurahResponse }

        quranRepository.getAllSurah()

        verify(local).coroutine { getSurah() }.wasInvoked(Times(2))
        verify(local).coroutine { insertSurah(allSurah) }.wasInvoked()
        verify(remote).coroutine { fetchAllSurah() }.wasInvoked()
    }

    /* @Test
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
    } */
}