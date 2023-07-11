package com.programmergabut.quranyuk.data.local

import com.programmergabut.quranyuk.QuranDatabase
import com.programmergabut.quranyuk.domain.model.Surah

class SqlDelightQuranDataSource(db: QuranDatabase): LocalDataSource {

    private val surahQueries = db.surahQueries

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


}