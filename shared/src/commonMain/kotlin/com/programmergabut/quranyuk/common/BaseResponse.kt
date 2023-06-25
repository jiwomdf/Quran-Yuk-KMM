package com.programmergabut.quranyuk.common

import kotlinx.serialization.SerialName

abstract class BaseResponse {
    @SerialName("code") var code: Int = 0
    @SerialName("status") var status: String = ""
}