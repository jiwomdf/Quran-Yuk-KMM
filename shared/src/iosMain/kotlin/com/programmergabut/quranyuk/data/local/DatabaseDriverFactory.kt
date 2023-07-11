package com.programmergabut.quranyuk.data.local

import com.programmergabut.quranyuk.QuranDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(QuranDatabase.Schema, "quran.db")
    }
}