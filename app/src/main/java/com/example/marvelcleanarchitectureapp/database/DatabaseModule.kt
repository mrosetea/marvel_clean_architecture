package com.example.marvelcleanarchitectureapp.database

import androidx.room.Room
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "marvel_app_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<AppDatabase>().characterDao()
    }
}