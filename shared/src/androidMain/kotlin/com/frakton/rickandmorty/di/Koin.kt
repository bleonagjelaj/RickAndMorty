package com.frakton.rickandmorty.di

import com.frakton.rickandmorty.data.remote.RickAndMortyApi
import com.frakton.rickandmorty.domain.interactors.GetCharacterDetailsInteractor
import com.frakton.rickandmorty.domain.interactors.GetCharactersInteractor
import com.frakton.rickandmorty.domain.mappers.CharacterDetailsMapper
import com.frakton.rickandmorty.domain.mappers.CharactersMapper
import com.frakton.rickandmorty.domain.repositories.CharacterDetailsRepository
import com.frakton.rickandmorty.domain.repositories.CharactersRepository
import com.frakton.rickandmorty.domain.usecases.GetCharacterDetailsUseCase
import com.frakton.rickandmorty.domain.usecases.GetCharactersUseCase
import com.frakton.rickandmorty.viewmodels.AndroidCharactersViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

fun initKoin(): KoinApplication = startKoin {
    modules(listOf(commonModule, viewModelModule))
}

actual val commonModule = module {
    single { "https://rickandmortyapi.com/api" }
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                })
            }
        }
    }
    single { RickAndMortyApi(get(), get()) }
    single { GetCharacterDetailsInteractor(get()) }
    single { GetCharactersInteractor(get()) }
    single { CharacterDetailsRepository(get(), get()) }
    single { CharactersRepository(get(), get()) }
    single { CharacterDetailsMapper() }
    single { CharactersMapper() }
    factory { GetCharactersUseCase(get()) }
    factory { GetCharacterDetailsUseCase(get()) }
}

val viewModelModule: Module = module {
    viewModel { AndroidCharactersViewModel() }
}