package com.programmergabut.quranyuk.util

import com.programmergabut.quranyuk.data.remote.response.allsurah.AllSurahResponse
import com.programmergabut.quranyuk.data.remote.response.readsurah.ReadSurahEnResponse
import com.programmergabut.quranyuk.domain.model.LastRead
import com.programmergabut.quranyuk.domain.model.ReadSurah
import com.programmergabut.quranyuk.domain.model.Surah

const val surahId = 1

val allSurahResponse = AllSurahResponse(
    data = listOf(
        AllSurahResponse.AllSurah(
            number = 1,
            englishName = "Al-Faatiha",
            englishNameTranslation = "The Opening",
            name = "سُورَةُ ٱلْفَاتِحَةِ",
            numberOfAyahs = 7,
            revelationType = "Meccan",
        ),
        AllSurahResponse.AllSurah(
            number = 2,
            englishName = "Al-Baqara",
            englishNameTranslation = "The Cow",
            name = "سُورَةُ البَقَرَةِ",
            numberOfAyahs = 286,
            revelationType = "Medinan",
        ),
    )
)

val allSurah = listOf(
    Surah(
        number = 1,
        englishName = "Al-Faatiha",
        englishNameTranslation = "The Opening",
        name = "سُورَةُ ٱلْفَاتِحَةِ",
        numberOfAyahs = 7,
        revelationType = "Meccan",
    ),
    Surah(
        number = 2,
        englishName = "Al-Baqara",
        englishNameTranslation = "The Cow",
        name = "سُورَةُ البَقَرَةِ",
        numberOfAyahs = 286,
        revelationType = "Medinan",
    ),
)

val readSurah = ReadSurah(
    number = 1,
    name = "سُورَةُ ٱلْفَاتِحَةِ",
    englishName = "Al-Faatiha",
    englishNameTranslation = "The Opening",
    ayah = listOf(
        ReadSurah.Ayah (
            number = 1,
            text = "بِسۡمِ ٱللَّهِ ٱلرَّحۡمَـٰنِ ٱلرَّحِیمِ",
            textEn = "In the name of God, The Most Gracious, The Dispenser of Grace",
            numberInSurah = 1,
        ),
        ReadSurah.Ayah (
            number = 2,
            text = "ٱلۡحَمۡدُ لِلَّهِ رَبِّ ٱلۡعَـٰلَمِینَ",
            textEn = "All praise is due to God alone, the Sustainer of all the worlds",
            numberInSurah = 2,
        )
    ),
)

val readSurahEnResponse = ReadSurahEnResponse(
    data = ReadSurahEnResponse.ReadSurahEn(
        number = 1,
        name = "سُورَةُ ٱلْفَاتِحَةِ",
        englishName = "Al-Faatiha",
        englishNameTranslation = "The Opening",
        revelationType = "Meccan",
        numberOfAyahs = 7,
        ayahs = listOf(
            ReadSurahEnResponse.ReadSurahEn.Ayah(
                number = 1,
                text = "In the name of God, The Most Gracious, The Dispenser of Graceِ",
                numberInSurah = 1,
                juz = 1,
                manzil = 1,
                page = 1,
                ruku = 1,
                hizbQuarter = 1,
                sajda = false,
            ),
            ReadSurahEnResponse.ReadSurahEn.Ayah(
                number = 2,
                text = "All praise is due to God alone, the Sustainer of all the worlds",
                numberInSurah = 2,
                juz = 1,
                manzil = 1,
                page = 1,
                ruku = 1,
                hizbQuarter = 1,
                sajda = false,
            )
        ),
    )
)

val readSurahArResponse = ReadSurahEnResponse(
    data = ReadSurahEnResponse.ReadSurahEn(
        number = 1,
        name = "سُورَةُ ٱلْفَاتِحَةِ",
        englishName = "Al-Faatiha",
        englishNameTranslation = "The Opening",
        revelationType = "Meccan",
        numberOfAyahs = 7,
        ayahs = listOf(
            ReadSurahEnResponse.ReadSurahEn.Ayah(
                number = 1,
                text = "بِسۡمِ ٱللَّهِ ٱلرَّحۡمَـٰنِ ٱلرَّحِیمِ",
                numberInSurah = 1,
                juz = 1,
                manzil = 1,
                page = 1,
                ruku = 1,
                hizbQuarter = 1,
                sajda = false,
            ),
            ReadSurahEnResponse.ReadSurahEn.Ayah(
                number = 2,
                text = "ٱلۡحَمۡدُ لِلَّهِ رَبِّ ٱلۡعَـٰلَمِینَ",
                numberInSurah = 2,
                juz = 1,
                manzil = 1,
                page = 1,
                ruku = 1,
                hizbQuarter = 1,
                sajda = false,
            )
        ),
    )
)

val lastRead = LastRead(
    ayahId = 1,
    surahId = 1,
    surahName = "Al-Faatiha",
)