package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.database.AppDatabase
import com.example.rickandmorty.util.AppModule
import com.example.rickandmorty.util.ViewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RickAndMortyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.createDatabase(applicationContext)
        startKoin {
            androidContext(this@RickAndMortyApp)
            modules(
                listOf(
                    AppModule.appModule,
                    ViewModelModules.characterViewModule,
                    ViewModelModules.episodesViewModule,
                    ViewModelModules.locationsViewModule

                )
            )
        }
    }
}