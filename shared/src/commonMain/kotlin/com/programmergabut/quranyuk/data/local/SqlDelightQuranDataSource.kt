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

    override suspend fun insertAyah(ayah: ReadSurahEn) {
        ayah.ayah?.forEach { ayahItem ->
            ayahQueries.insertAyah(
                ayahID = ayahItem.number.toLong(),
                surahID = ayahItem.number.toLong(),
                juz = ayahItem.juz.toLong(),
                number = ayahItem.number.toLong(),
                numberInSurah = ayahItem.numberInSurah.toLong(),
                text = ayahItem.text,
                englishName = ayah.englishName ?: "",
                englishNameTranslation = ayah.englishName ?: "",
                name = ayah.name ?: "",
                numberOfAyahs = ayahItem.number.toString(),
                revelationType = ayahItem.number.toString(),
                textEn = ayahItem.textEng,
                isLastRead = ayahItem.number.toString()
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
