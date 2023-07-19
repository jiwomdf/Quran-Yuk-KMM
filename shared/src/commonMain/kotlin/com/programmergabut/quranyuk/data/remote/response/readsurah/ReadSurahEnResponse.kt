package com.programmergabut.quranyuk.data.remote.response.readsurah

import com.programmergabut.quranyuk.common.BaseResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReadSurahEnResponse(
    @SerialName("data") var data: ReadSurahEn?
): BaseResponse() {

    @Serializable
    data class ReadSurahEn(
        @SerialName("number") val number: Int?,
        @SerialName("name") val name: String?,
        @SerialName("englishName") val englishName: String?,
        @SerialName("englishNameTranslation") val englishNameTranslation: String?,
        @SerialName("revelationType") val revelationType: String?,
        @SerialName("numberOfAyahs") val numberOfAyahs: Int?,
        @SerialName("ayahs") var ayahs: List<Ayah?>?,
    ) {

        @Serializable
        data class Ayah(
            @SerialName("number") val number: Int?,
            @SerialName("text") val text: String?,
            @SerialName("numberInSurah") val numberInSurah: Int?,
            @SerialName("juz") val juz: Int?,
            @SerialName("manzil") val manzil: Int?,
            @SerialName("page") val page: Int?,
            @SerialName("ruku") val ruku: Int?,
            @SerialName("hizbQuarter") val hizbQuarter: Int?,
            @SerialName("sajda") val sajda: Boolean?
        )
    }
}