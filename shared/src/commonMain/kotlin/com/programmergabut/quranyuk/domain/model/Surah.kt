package com.programmergabut.quranyuk.domain.model

import com.programmergabut.quranyuk.data.remote.response.AllSurahResponse
import database.SurahEntity

data class Surah(
    val englishName: String,
    val englishNameTranslation: String,
    val name: String,
    val number: Long,
    val numberOfAyahs: Long,
    val revelationType: String
) {

    companion object {
        fun mapAllSurah(response: AllSurahResponse): List<Surah> {
            return response.data?.map {
                Surah(
                    englishName = it.englishName ?: "",
                    englishNameTranslation = it.englishNameTranslation ?: "",
                    name = it.name ?: "",
                    number = it.number ?: 0,
                    numberOfAyahs = it.numberOfAyahs ?: 0,
                    revelationType = it.revelationType ?: "",
                )
            } ?: emptyList()
        }

        fun mapAllSurah(entity: List<SurahEntity>): List<Surah> {
            return entity.map {
                Surah(
                    englishName = it.englishName ?: "",
                    englishNameTranslation = it.englishNameTranslation ?: "",
                    name = it.name ?: "",
                    number = it.number ?: 0,
                    numberOfAyahs = it.numberOfAyahs ?: 0,
                    revelationType = it.revelationType ?: "",
                )
            }
        }
    }
}