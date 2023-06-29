package com.example.rickandmorty.domain.models

data class CharacterDetailsModel(
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: String,
    val pictureUrl: String
)