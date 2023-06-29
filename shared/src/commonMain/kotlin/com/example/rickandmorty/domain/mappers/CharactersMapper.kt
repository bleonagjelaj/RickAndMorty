package com.example.rickandmorty.domain.mappers

import com.example.rickandmorty.data.remote.models.CharactersResponse
import com.example.rickandmorty.domain.models.CharacterModel

class CharactersMapper {
    fun map(characters: CharactersResponse): List<CharacterModel> {
        val characterList = arrayListOf<CharacterModel>()
        characters.results.forEach { character ->
            characterList.add(
                CharacterModel(
                    id = character.id,
                    name = character.name,
                    pictureUrl = character.image
                )
            )
        }
        return characterList
    }
}