package com.programmergabut.quranyuk.domain.model

import com.programmergabut.quranyuk.data.remote.response.ReadSurahEnResponse


data class ReadSurahEn(
    val number: Int,
    val name: String,
    val englishName: String,
    val ayah: List<Ayah>,
) {

    data class Ayah(
        val number: Int,
        val text: String,
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
            return response.data?.let {
                ReadSurahEn(
                    number = it.number ?: 0,
                    name = it.name ?: "",
                    englishName = it.englishName ?: "",
                    ayah = it.ayahs?.map {
                        Ayah(
                            number = it.number ?: 0,
                            text = it.text ?: "",
                            numberInSurah = it.numberInSurah ?: 0,
                            juz = it.juz ?: 0,
                            manzil = it.manzil ?: 0,
                            page = it.page ?: 0,
                            ruku = it.ruku ?: 0,
                            hizbQuarter = it.hizbQuarter ?: 0,
                            sajda = it.sajda ?: false,
                        )
                    } ?: emptyList()
                )
            }

        }
    }
}