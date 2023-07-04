package com.frakton.rickandmorty.domain.interactors

import com.frakton.rickandmorty.data.remote.RickAndMortyApi
import com.frakton.rickandmorty.data.remote.models.Character
import org.koin.core.component.KoinComponent

class GetCharacterDetailsInteractor(
    private val api: RickAndMortyApi
) : KoinComponent {
    suspend operator fun invoke(characterId: Int): Character {
        return api.getCharacter(characterId)
    }
}