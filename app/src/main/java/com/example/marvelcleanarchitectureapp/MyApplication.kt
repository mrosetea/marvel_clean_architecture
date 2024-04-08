package com.example.marvelcleanarchitectureapp

import android.app.Application
import com.example.marvelcleanarchitectureapp.database.databaseModule
import com.example.marvelcleanarchitectureapp.modules.home.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MyApplication)
            modules(listOf(databaseModule, homeModule))
        }
    }
}