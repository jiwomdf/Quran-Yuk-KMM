package com.programmergabut.quranyuk.android

import android.app.Application
import com.programmergabut.quranyuk.android.di.driverModule
import com.programmergabut.quranyuk.android.di.localModule
import com.programmergabut.quranyuk.android.di.remoteModule
import com.programmergabut.quranyuk.android.di.repositoryModule
import com.programmergabut.quranyuk.android.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(listOf(
                viewModelModule,
                repositoryModule,
                driverModule,
                remoteModule,
                localModule
            ))
        }
    }
}