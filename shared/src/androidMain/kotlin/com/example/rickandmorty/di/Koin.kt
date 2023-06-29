package com.example.rickandmorty.di

import com.example.rickandmorty.data.remote.RickAndMortyApi
import com.example.rickandmorty.domain.interactors.GetCharacterDetailsInteractor
import com.example.rickandmorty.domain.interactors.GetCharactersInteractor
import com.example.rickandmorty.domain.mappers.CharacterDetailsMapper
import com.example.rickandmorty.domain.mappers.CharactersMapper
import com.example.rickandmorty.domain.repositories.CharacterDetailsRepository
import com.example.rickandmorty.domain.repositories.CharactersRepository
import com.example.rickandmorty.domain.usecases.GetCharacterDetailsUseCase
import com.example.rickandmorty.domain.usecases.GetCharactersUseCase
import com.example.rickandmorty.viewmodels.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

fun initKoin(): KoinApplication = startKoin {
    modules(listOf(commonModule, viewModelModule))
}

actual val commonModule = module {
    single { RickAndMortyApi() }
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
    viewModel { CharactersViewModel(get(), get()) }
}