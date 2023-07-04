package com.example.rickandmorty.domain.states

import com.example.rickandmorty.domain.models.CharacterModel

data class CharactersState(
    val characters: List<CharacterModel> = listOf()
)