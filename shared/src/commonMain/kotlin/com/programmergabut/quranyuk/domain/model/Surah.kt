package com.programmergabut.quranyuk.domain.model

import com.programmergabut.quranyuk.data.remote.response.AllSurahResponse

data class Surah(
    val englishName: String,
    val englishNameTranslation: String,
    val name: String,
    val number: Int,
    val numberOfAyahs: Int,
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
    }
}