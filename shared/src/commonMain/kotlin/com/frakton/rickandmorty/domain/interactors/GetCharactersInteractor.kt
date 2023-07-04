package com.frakton.rickandmorty.domain.interactors

import com.frakton.rickandmorty.data.remote.RickAndMortyApi
import com.frakton.rickandmorty.data.remote.models.CharactersResponse
import org.koin.core.component.KoinComponent

class GetCharactersInteractor(private val api: RickAndMortyApi) : KoinComponent {
    suspend operator fun invoke(): CharactersResponse {
        return api.getAllCharacters()
    }
}