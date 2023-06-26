package com.programmergabut.quranyuk.domain.model

import com.programmergabut.quranyuk.data.remote.response.AllSurahResponse


data class Ayah(
    val number: Int,
    val text: String,
    val surah: Surah,
) {

    data class Surah(
        val number: Int,
        val name: String,
        val englishName: String,
        val englishNameTranslation: String,
        val revelationType: String,
        val numberOfAyahs: Int
    )

    companion object {
        fun mapAyah(response: AllSurahResponse): List<Ayah> {
            return response.data?.ayahs?.map {
                Ayah(
                    number = it.number ?: 0,
                    text = it.text ?: "",
                    surah = Surah(
                        number = it.number ?: 0,
                        name = it.surah?.name ?: "",
                        englishName = it.surah?.englishName ?: "",
                        englishNameTranslation = it.surah?.englishNameTranslation ?: "",
                        revelationType = it.surah?.revelationType ?: "",
                        numberOfAyahs = it.surah?.numberOfAyahs ?: 0,
                    )
                )
            } ?: emptyList()

        }
    }
}