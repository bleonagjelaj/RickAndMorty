package com.example.rickandmorty.domain.repositories

import com.example.rickandmorty.domain.interactors.GetCharactersInteractor
import com.example.rickandmorty.domain.mappers.CharactersMapper
import com.example.rickandmorty.domain.models.CharacterModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent

class CharactersRepository(
    private val getCharactersInteractor: GetCharactersInteractor,
    private val charactersMapper: CharactersMapper
) : KoinComponent {
    suspend fun getAllCharacters(): Flow<List<CharacterModel>> = flow {
        emit(charactersMapper.map(getCharactersInteractor.invoke()))
    }
}