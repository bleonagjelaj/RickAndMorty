package com.example.rickandmorty.domain.interactors

import com.example.rickandmorty.data.remote.RickAndMortyApi
import com.example.rickandmorty.data.remote.models.Character
import org.koin.core.component.KoinComponent

class GetCharacterDetailsInteractor(
    private val api: RickAndMortyApi
) : KoinComponent {
    suspend operator fun invoke(characterId: Int): Character {
        return api.getCharacter(characterId)
    }
}