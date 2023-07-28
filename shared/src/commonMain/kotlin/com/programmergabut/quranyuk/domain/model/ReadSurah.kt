package com.programmergabut.quranyuk.domain.model

import com.programmergabut.quranyuk.data.remote.response.readsurah.ReadSurahCombinedResponse
import database.AyahEntity


data class ReadSurah(
    val number: Int,
    val name: String,
    val englishName: String,
    val englishNameTranslation: String,
    val ayah: List<Ayah>,
) {

    data class Ayah(
        val number: Int,
        val text: String,
        val textEn: String,
        val numberInSurah: Int,
    )

    companion object {
        fun mapReadSurahEn(response: ReadSurahCombinedResponse?): ReadSurah? {
            return response?.let { readSurah ->
                ReadSurah(
                    number = readSurah.number ?: 0,
                    name = readSurah.name ?: "",
                    englishName = readSurah.englishName ?: "",
                    englishNameTranslation = readSurah.englishNameTranslation ?: "",
                    ayah = readSurah.ayahCombined?.map {
                        Ayah(
                            number = it?.number ?: 0,
                            text = it?.text ?: "",
                            textEn = it?.textEn ?: "",
                            numberInSurah = it?.numberInSurah ?: 0,
                        )
                    } ?: emptyList()
                )
            }
        }

        fun mapReadSurahEnToEntity(entity: List<AyahEntity>?): ReadSurah? {
            return if ((entity?.size ?: 0) > 0) {
                entity?.let { ayah ->
                    ReadSurah(
                        number = ayah[0].surahId.toInt(),
                        name = ayah[0].name,
                        englishName = ayah[0].englishName,
                        englishNameTranslation = ayah[0].englishNameTranslation,
                        ayah = ayah.map {
                            Ayah(
                                number = it.number.toInt(),
                                text = it.text,
                                textEn = it.textEn,
                                numberInSurah = it.numberInSurah.toInt(),
                            )
                        }
                    )
                } ?: kotlin.run {
                    null
                }
            } else {
                null
            }
        }
    }
}