package com.programmergabut.quranyuk.data.remote.response

import com.programmergabut.quranyuk.common.BaseResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllSurahResponse(
    @SerialName("data") var data: List<AllSurah>? = null
): BaseResponse() {
    @Serializable
    data class AllSurah(
        @SerialName("englishName") val englishName: String?,
        @SerialName("englishNameTranslation") val englishNameTranslation: String?,
        @SerialName("name") val name: String?,
        @SerialName("number") val number: Long?,
        @SerialName("numberOfAyahs") val numberOfAyahs: Long?,
        @SerialName("revelationType") val revelationType: String?
    )
}