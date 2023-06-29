package com.example.rickandmorty.domain.models

data class CharacterModel(
    val id: Int,
    val name: String,
    val pictureUrl: String
) {
    companion object {
        fun initModel(): CharacterModel {
            return CharacterModel(-1, "", "")
        }
    }
}
