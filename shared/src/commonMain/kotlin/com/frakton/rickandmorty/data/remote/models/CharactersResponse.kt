package com.frakton.rickandmorty.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponse(
    @SerialName("results")
    val results: List<Character>
)

@Serializable
data class Character(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("status")
    val status: String,
    @SerialName("species")
    val species: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("origin")
    val origin: Origin,
    @SerialName("image")
    val image: String
)

@Serializable
data class Origin(
    @SerialName("name")
    val name: String
)