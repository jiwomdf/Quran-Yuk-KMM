package com.programmergabut.quranyuk.data.local

import com.programmergabut.quranyuk.QuranDatabase
import com.programmergabut.quranyuk.domain.model.ReadSurahEn
import com.programmergabut.quranyuk.domain.model.ReadSurahEng2
import com.programmergabut.quranyuk.domain.model.Surah

class SqlDelightQuranDataSource(db: QuranDatabase): LocalDataSource {

    private val surahQueries = db.surahQueries
    private val ayahQueries = db.ayahQueries

    override suspend fun insertSurah(surah: List<Surah>) {
        surah.forEach {
            val lowerEngName = it.englishName.lowercase().replace("-"," ").trim()
            surahQueries.insertSurah(
                number = it.number,
                englishName = it.englishName,
                englishNameFormatted = it.englishName,
                englishNameTranslation = lowerEngName,
                name = it.name,
                numberOfAyahs = it.numberOfAyahs,
                revelationType = it.revelationType,
            )
        }
    }

    override suspend fun getSurah(): List<Surah> {
        return Surah.mapAllSurah(
            surahQueries
                .getSurahs()
                .executeAsList()
        )
    }

    override suspend fun insertAyah(readSurah: ReadSurahEn) {
        readSurah.ayah?.forEach {
            ayahQueries.insertAyah(
                ayahID = it.number.toLong(),
                surahID = readSurah.number?.toLong() ?: 0L ,
                juz = it.juz.toLong(),
                number = it.number.toLong(),
                numberInSurah = it.numberInSurah.toLong(),
                text = it.text,
                englishName = readSurah.englishName ?: "",
                englishNameTranslation = readSurah.englishName ?: "",
                name = readSurah.name ?: "",
                numberOfAyahs = it.number.toString(),
                revelationType = it.number.toString(),
                textEn = it.textEng,
            )
        }
    }


    override suspend fun getAyah(): ReadSurahEn {
        return ReadSurahEn.mapReadSurahEnToEntity(
            ayahQueries
                .getAyahsBySurahID()
                .executeAsOne()
        )
    }

}
