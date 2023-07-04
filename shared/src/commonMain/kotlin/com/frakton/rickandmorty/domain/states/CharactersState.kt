package com.frakton.rickandmorty.domain.states

import com.frakton.rickandmorty.domain.models.CharacterModel

data class CharactersState(
    val characters: List<CharacterModel> = listOf()
)