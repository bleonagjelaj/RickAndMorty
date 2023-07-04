package com.example.rickandmorty.domain.states

import com.example.rickandmorty.domain.models.CharacterDetailsModel

data class CharacterDetailsState(
    val characterDetails: CharacterDetailsModel =
        CharacterDetailsModel(
            name = "",
            status = "",
            species = "",
            gender = "",
            origin = "",
            pictureUrl = ""
        )
)