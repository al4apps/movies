package com.al4apps.movies

import android.app.Application
import com.al4apps.movies.di.dataModule
import com.al4apps.movies.di.domainModule
import com.al4apps.movies.di.uiModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(dataModule, domainModule, uiModule)
        }
    }
}