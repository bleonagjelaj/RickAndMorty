package com.example.rickandmorty.domain.mappers

import com.example.rickandmorty.data.remote.models.Character
import com.example.rickandmorty.domain.models.CharacterDetailsModel

class CharacterDetailsMapper {
    fun map(character: Character) = CharacterDetailsModel(
        name = character.name,
        status = character.status,
        species = character.species,
        gender = character.gender,
        origin = character.origin.name,
        pictureUrl = character.image
    )
}