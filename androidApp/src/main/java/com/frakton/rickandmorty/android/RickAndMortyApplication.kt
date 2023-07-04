package com.frakton.rickandmorty.android

import android.app.Application
import com.frakton.rickandmorty.di.initKoin

class RickAndMortyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
    }
}