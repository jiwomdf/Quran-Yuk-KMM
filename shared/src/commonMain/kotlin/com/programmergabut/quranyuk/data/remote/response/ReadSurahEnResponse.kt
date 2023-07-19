package com.programmergabut.quranyuk.data.remote.response

import com.programmergabut.quranyuk.common.BaseResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReadSurahEnResponse(
    @SerialName("data") var data: ReadSurahEn? = null
): BaseResponse() {

    @Serializable
    data class ReadSurahEn(
        @SerialName("number") val number: Int? = null,
        @SerialName("name") val name: String? = null,
        @SerialName("englishName") val englishName: String? = null,
        @SerialName("englishNameTranslation") val englishNameTranslation: String? = null,
        @SerialName("revelationType") val revelationType: String? = null,
        @SerialName("numberOfAyahs") val numberOfAyahs: Int? = null,
        @SerialName("ayahs") var ayahs: List<Ayah>? = null,
    ) {

        @Serializable
        data class Ayah(
            @SerialName("number") val number: Int? = null,
            @SerialName("text") val text: String? = null,
            @SerialName("numberInSurah") val numberInSurah: Int? = null,
            @SerialName("juz") val juz: Int? = null,
            @SerialName("manzil") val manzil: Int? = null,
            @SerialName("page") val page: Int? = null,
            @SerialName("ruku") val ruku: Int? = null,
            @SerialName("hizbQuarter") val hizbQuarter: Int? = null,
            @SerialName("sajda") val sajda: Boolean? = null,

            @SerialName("textEn") var textEn: String? = null,

        )
    }
}