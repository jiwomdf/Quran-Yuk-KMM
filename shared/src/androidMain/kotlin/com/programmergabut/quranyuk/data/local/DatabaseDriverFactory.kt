package com.programmergabut.quranyuk.data.local

import android.content.Context
import com.programmergabut.quranyuk.QuranDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(QuranDatabase.Schema, context, "quran.db")
    }
}