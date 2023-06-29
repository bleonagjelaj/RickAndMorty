package com.example.rickandmorty.android

import android.app.Application
import com.example.rickandmorty.di.initKoin

class RickAndMortyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
    }
}