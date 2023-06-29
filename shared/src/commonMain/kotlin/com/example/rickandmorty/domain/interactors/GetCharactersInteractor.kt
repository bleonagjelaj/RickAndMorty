package com.example.rickandmorty.domain.interactors

import com.example.rickandmorty.data.remote.RickAndMortyApi
import com.example.rickandmorty.data.remote.models.CharactersResponse
import org.koin.core.component.KoinComponent

class GetCharactersInteractor(private val api: RickAndMortyApi) : KoinComponent {
    suspend operator fun invoke(): CharactersResponse {
        return api.getAllCharacters()
    }
}