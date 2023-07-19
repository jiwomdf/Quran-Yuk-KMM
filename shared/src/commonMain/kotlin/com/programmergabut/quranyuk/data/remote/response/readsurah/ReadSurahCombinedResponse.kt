package com.programmergabut.quranyuk.data.remote.response.readsurah

import com.programmergabut.quranyuk.common.BaseResponse

data class ReadSurahCombinedResponse(
    val number: Int?,
    val name: String?,
    val englishName: String?,
    val englishNameTranslation: String?,
    val revelationType: String?,
    val numberOfAyahs: Int?,
    var ayahCombined: List<AyahCombined?>?,
): BaseResponse() {

    data class AyahCombined(
        val number: Int?,
        val text: String?,
        var textEn: String?,
        val numberInSurah: Int?,
        val juz: Int?,
        val manzil: Int?,
        val page: Int?,
        val ruku: Int?,
        val hizbQuarter: Int?,
        val sajda: Boolean?
    )

    companion object {
        fun mapReadSurahCombinedResponse(
            arResponse: ReadSurahEnResponse?,
            enResponse: ReadSurahEnResponse?
        ): ReadSurahCombinedResponse? {
            val hashMapOfAyah = hashMapOf<Int, AyahCombined?>()
            val combinedResponse = arResponse?.data?.let {
                ReadSurahCombinedResponse(
                    number = it.number,
                    name = it.name,
                    englishName = it.englishName,
                    englishNameTranslation = it.englishNameTranslation,
                    revelationType = it.revelationType,
                    numberOfAyahs = it.numberOfAyahs,
                    ayahCombined = it.ayahs?.map {
                        AyahCombined(
                            number = it?.number,
                            text = it?.text,
                            numberInSurah = it?.numberInSurah,
                            juz = it?.juz,
                            manzil = it?.manzil,
                            page = it?.page,
                            ruku = it?.ruku,
                            hizbQuarter = it?.hizbQuarter,
                            sajda = it?.sajda,
                            textEn = null
                        )
                    }
                )
            }

            combinedResponse?.ayahCombined?.indices?.forEach { i ->
                if (hashMapOfAyah[i] == null) {
                    hashMapOfAyah[i] =  combinedResponse.ayahCombined?.get(i)
                }
            }

            enResponse?.data?.ayahs?.indices?.forEach { i ->
                if (hashMapOfAyah[i] != null) {
                    hashMapOfAyah[i]?.textEn = enResponse.data?.ayahs?.get(i)?.text ?: ""
                }
            }

            combinedResponse?.ayahCombined = hashMapOfAyah.values.toList()
            return combinedResponse
        }
    }
}