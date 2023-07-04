package com.frakton.rickandmorty.domain.states

import com.frakton.rickandmorty.domain.models.CharacterDetailsModel

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