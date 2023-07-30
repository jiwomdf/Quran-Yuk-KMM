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

    @Test
    fun `getAllSurah has surahs in db_ just has getSurah interaction`(): Unit = runBlocking {
        given(local).coroutine { getSurah() }.then { allSurah }
        given(local).coroutine { insertSurah(allSurah) }.thenReturn(Unit)
        given(remote).coroutine { fetchAllSurah() }.then { allSurahResponse }

        quranRepository.getAllSurah()

        verify(local).coroutine { getSurah() }.wasInvoked(Times(2))
        verify(local).coroutine { insertSurah(allSurah) }.wasNotInvoked()
        verify(remote).coroutine { fetchAllSurah() }.wasNotInvoked()
    }

    @Test
    fun `getReadSurah empty surah in db_ has all interaction`() : Unit = runBlocking {
        val readSurah = ReadSurahCombinedResponse
            .mapReadSurahCombinedResponse(readSurahArResponse, readSurahEnResponse)

        given(local).coroutine { getAyah(surahId) }.thenReturn(null)
        given(remote).coroutine { fetchReadSurahAr(surahId) }.thenReturn(readSurahArResponse)
        given(remote).coroutine { fetchReadSurahEn(surahId) }.thenReturn(readSurahEnResponse)
        ReadSurah.mapReadSurahEn(readSurah)?.let { given(local).coroutine { insertAyah(it) }.thenReturn(Unit) }

        quranRepository.getReadSurah(surahId)

        verify(remote).coroutine { fetchReadSurahAr(surahId) }.wasInvoked()
        verify(remote).coroutine { fetchReadSurahEn(surahId) }.wasInvoked()
        ReadSurah.mapReadSurahEn(readSurah)?.let { verify(local).coroutine { insertAyah(it) }.wasInvoked() }
    }

    @Test
    fun `getReadSurah has surahs in db_ just has getSurah interaction`() : Unit = runBlocking {
        given(local).coroutine { getAyah(surahId) }.thenReturn(readSurah)
        given(local).coroutine { insertAyah(readSurah) }.thenReturn(Unit)
        given(remote).coroutine { fetchReadSurahAr(surahId) }.thenReturn(readSurahArResponse)
        given(remote).coroutine { fetchReadSurahEn(surahId) }.thenReturn(readSurahEnResponse)

        quranRepository.getReadSurah(surahId)

        verify(local).coroutine { getAyah(surahId) }.wasInvoked(Times(2))
        verify(local).coroutine { insertAyah(readSurah) }.wasNotInvoked()
        verify(remote).coroutine { fetchReadSurahAr(surahId) }.wasNotInvoked()
        verify(remote).coroutine { fetchReadSurahEn(surahId) }.wasNotInvoked()
    }

    @Test
    fun `getLastRead_ local getLastRead called`() : Unit = runBlocking {
        given(local).coroutine { getLastRead() }.thenReturn(lastRead)

        quranRepository.getLastRead()
        verify(local).coroutine { getLastRead() }.wasInvoked()
    }

    @Test
    fun insertLastRead() : Unit = runBlocking {
        given(local).coroutine { insertLastRead(lastRead.ayahId, lastRead.surahId, lastRead.surahName) }.thenReturn(Unit)

        quranRepository.insertLastRead(lastRead.ayahId, lastRead.surahId, lastRead.surahName)
        verify(local).coroutine { insertLastRead(lastRead.ayahId, lastRead.surahId, lastRead.surahName) }.wasInvoked()
    }
}