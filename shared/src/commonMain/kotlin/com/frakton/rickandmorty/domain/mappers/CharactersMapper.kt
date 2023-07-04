package com.frakton.rickandmorty.domain.mappers

import com.frakton.rickandmorty.data.remote.models.CharactersResponse
import com.frakton.rickandmorty.domain.models.CharacterModel

class CharactersMapper {
    fun map(characters: CharactersResponse): List<CharacterModel> {
        val characterList = arrayListOf<CharacterModel>()
        characters.results.forEach { character ->
            characterList.add(
                CharacterModel(
                    characterId = character.id,
                    name = character.name,
                    pictureUrl = character.image
                )
            )
        }
        return characterList
    }
}