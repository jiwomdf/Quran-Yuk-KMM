package com.programmergabut.quranyuk.domain.model

import com.programmergabut.quranyuk.data.remote.response.ReadSurahEnResponse
import database.AyahEntity
import database.SurahEntity


data class ReadSurahEn(
    val number: Int? = 0,
    val name: String? = "",
    val englishName: String? = "",
    val ayah: List<Ayah>? = emptyList(),
) {

    data class Ayah(
        val number: Int,
        val text: String,
        val textEng: String,
        val numberInSurah: Int,
        val juz: Int,
        val manzil: Int,
        val page: Int,
        val ruku: Int,
        val hizbQuarter: Int,
        val sajda: Boolean,
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
                            number = it.number ?: 0,
                            text = it.text ?: "",
                            textEng = it.text ?: "",
                            numberInSurah = it.numberInSurah ?: 0,
                            juz = it.juz ?: 0,
                            manzil = it.manzil ?: 0,
                            page = it.page ?: 0,
                            ruku = it.ruku ?: 0,
                            hizbQuarter = it.hizbQuarter ?: 0,
                            sajda = it.sajda ?: false,
                        )
                    }
                )
            }

        }

//        fun mapReadSurahEnToEntity(response: ReadSurahEn): AyahEntity {
//            return response.let {
//                AyahEntity(
//                    ayahID = it.ayah?.get(0)?.number!!.toLong(),
//                    surahID = it.ayah[0].number.toLong() ,
//                    juz = it.ayah.get(0).juz.toLong(),
//                    number = it.ayah.get(0).number.toLong(),
//                    numberInSurah = it.ayah[0].numberInSurah.toLong(),
//                    text = it.ayah[0].text,
//                    englishName= it.englishName ?: "",
//                    englishNameTranslation = it.englishName ?: "",
//                    name = it.name ?: "",
//                    numberOfAyahs = it.ayah.get(0).number.toString(),
//                    revelationType = it.ayah.get(0).number.toString(),
//                    textEn = it.ayah[0].textEng,
//                    isLastRead = it.ayah.get(0).number.toString()
//                )
//
//            }
//        }

        fun mapReadSurahEnToEntity(entity: AyahEntity): ReadSurahEn {
            return entity.let { ayah ->
                ReadSurahEn(
                    number = ayah.number.toInt(),
                    name = ayah.name ,
                    englishName = ayah.englishName ,
                    ayah = emptyList<Ayah>().map{
                        Ayah(
                            number = it.number,
                            text = it.text ,
                            textEng = it.text ,
                            numberInSurah = it.numberInSurah,
                            juz = it.juz ,
                            manzil = it.manzil ,
                            page = it.page ,
                            ruku = it.ruku ,
                            hizbQuarter = it.hizbQuarter ,
                            sajda = it.sajda ,
                        )
                    }
                )
            }
        }

    }
}