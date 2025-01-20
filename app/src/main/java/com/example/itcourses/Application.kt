package com.example.itcourses

import android.app.Application
import com.example.itcourses.di.appModule
import com.example.itcourses.di.coreModule
import com.example.itcourses.di.dataModule
import com.example.itcourses.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        configureDI()
    }

    private fun configureDI() = startKoin {
        androidContext(this@Application)
        modules(
            coreModule,
            dataModule,
            domainModule,
            appModule
        )

    }
}