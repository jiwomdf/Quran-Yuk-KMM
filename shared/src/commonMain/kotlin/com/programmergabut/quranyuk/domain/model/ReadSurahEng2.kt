package com.programmergabut.quranyuk.domain.model

import com.squareup.sqldelight.Query
import database.AyahEntity

data class ReadSurahEng2(
    val number: Int? = 0,
    val name: String? = "",
    val englishName: String? = "",
    val text: String? = "" ,
    val textEng: String? = "",
    val numberInSurah: Int? = 0,
    val juz: Int? = 0,
) {
    companion object{
        fun mapEntityToSurahEng2(entity: AyahEntity): ReadSurahEng2 {
            return entity.let {
                ReadSurahEng2(
                    number = it.number.toInt(),
                    name = it.name ?: "",
                    englishName = it.englishName ?: "",
                    text = it.text ?: "",
                    textEng = it.text ?: "",
                    numberInSurah = it.numberInSurah.toInt(),
                    juz = it.juz.toInt(),
                )
            }
        }
    }
}