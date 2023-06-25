package com.programmergabut.quranyuk.data.remote.response

import com.programmergabut.quranyuk.common.BaseResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllSurahResponse(
    @SerialName("data") var data: AllSurah? = null
): BaseResponse() {

    @Serializable
    data class AllSurah(
        @SerialName("number") val number: Int? = null,
        @SerialName("ayahs") var ayahs: List<Ayah>? = null,
    )

    @Serializable
    data class Ayah(
        @SerialName("number") val number: Int? = null,
        @SerialName("text") val text: String? = null,
        @SerialName("surah") val surah: Surah? = null,
    )

    @Serializable
    data class Surah(
        @SerialName("number") val number: Int? = null,
        @SerialName("name") val name: String? = null,
        @SerialName("englishName") val englishName: String? = null,
        @SerialName("englishNameTranslation") val englishNameTranslation: String? = null,
        @SerialName("revelationType") val revelationType: String? = null,
        @SerialName("numberOfAyahs") val numberOfAyahs: Int? = null
    )
}