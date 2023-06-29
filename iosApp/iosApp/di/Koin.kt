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
import org.koin.core.module.Module
import org.koin.dsl.module

actual val commonModule: Module = module {
    single { RickAndMortyApi() }
    single { GetCharacterDetailsInteractor() }
    single { GetCharactersInteractor() }
    single { CharacterDetailsRepository() }
    single { CharactersRepository() }
    single { CharacterDetailsMapper() }
    single { CharactersMapper() }
    single { GetCharactersUseCase() }
    single { GetCharacterDetailsUseCase() }
    single { CharactersRepository() }
    single { CharacterDetailsRepository() }
}