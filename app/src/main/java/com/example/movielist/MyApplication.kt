package com.example.movielist

import android.app.Application
import com.example.movielist.core.di.databaseModule
import com.example.movielist.core.di.networkModule
import com.example.movielist.core.di.repositoryModule
import com.example.movielist.core.utils.ReleaseTree
import com.example.movielist.di.useCaseModule
import com.example.movielist.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }else {
            Timber.plant(ReleaseTree())
        }
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}