package com.programmergabut.quranyuk.domain.model

import database.LastReadEntity

data class LastRead(
    val ayahId: Int,
    val surahId: Int
) {
    companion object {
        fun mapLastRead(lastReadEntity: LastReadEntity?): LastRead? {
            return lastReadEntity?.let {
                LastRead(
                    ayahId = it.ayahId.toInt(),
                    surahId = it.surahId.toInt(),
                )
            } ?: kotlin.run {
                null
            }
        }
    }
}