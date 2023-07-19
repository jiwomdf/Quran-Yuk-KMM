package com.programmergabut.quranyuk.domain.model

import com.programmergabut.quranyuk.data.remote.response.ReadSurahEnResponse
import database.AyahEntity


data class ReadSurahEn(
    val number: Int,
    val name: String,
    val englishName: String,
    val ayah: List<Ayah>,
) {

    data class Ayah(
        val number: Int,
        val text: String,
        val textEng: String,
        val numberInSurah: Int,
    )

    companion object {
        fun mapReadSurahEn(response: ReadSurahEnResponse): ReadSurahEn? {
            return response.data?.let { ayah ->
                ReadSurahEn(
                    number = ayah.number ?: 0,
                    name = ayah.name ?: "",
                    englishName = ayah.englishName ?: "",
                    ayah = ayah.ayahs?.map {
                        Ayah(
                            number = it?.number ?: 0,
                            text = it?.text ?: "",
                            textEng = it?.text ?: "",
                            numberInSurah = it?.numberInSurah ?: 0,
                        )
                    } ?: emptyList()
                )
            }
        }

        fun mapReadSurahEnToEntity(entity: List<AyahEntity>?): ReadSurahEn? {
            return entity?.let { ayah ->
                ReadSurahEn(
                    number = ayah[0].number.toInt(),
                    name = ayah[0].name,
                    englishName = ayah[0].englishName,
                    ayah = ayah.map{
                        Ayah(
                            number = it.number.toInt(),
                            text = it.text ,
                            textEng = it.text ,
                            numberInSurah = it.numberInSurah.toInt(),
                        )
                    }
                )
            }
        }
    }
}