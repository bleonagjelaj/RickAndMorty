package com.example.rickandmorty.data.remote

import com.example.rickandmorty.data.remote.models.Character
import com.example.rickandmorty.data.remote.models.CharactersResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class RickAndMortyApi {
    private val apiUrl = "https://rickandmortyapi.com/api"

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    suspend fun getAllCharacters(): CharactersResponse {
        return httpClient.get("$apiUrl/character").body()
    }

    suspend fun getCharacter(id: Int): Character {
        return httpClient.get("$apiUrl/character/$id").body()
    }
}