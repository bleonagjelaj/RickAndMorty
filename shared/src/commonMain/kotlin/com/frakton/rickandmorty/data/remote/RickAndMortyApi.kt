package com.frakton.rickandmorty.data.remote

import com.frakton.rickandmorty.data.remote.models.Character
import com.frakton.rickandmorty.data.remote.models.CharactersResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.koin.core.component.KoinComponent

class RickAndMortyApi(
    private val apiUrl: String,
    private val httpClient: HttpClient
): KoinComponent {
    suspend fun getAllCharacters(): CharactersResponse {
        return httpClient.get("$apiUrl/character").body()
    }

    suspend fun getCharacter(id: Int): Character {
        return httpClient.get("$apiUrl/character/$id").body()
    }
}