package com.example.rickandmorty.domain.repositories

import com.example.rickandmorty.domain.interactors.GetCharactersInteractor
import com.example.rickandmorty.domain.mappers.CharactersMapper
import com.example.rickandmorty.domain.models.CharacterModel
import com.example.rickandmorty.utils.CommonFlow
import com.example.rickandmorty.utils.toCommonFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent

class CharactersRepository(
    private val getCharactersInteractor: GetCharactersInteractor,
    private val charactersMapper: CharactersMapper
) : KoinComponent {
    suspend fun getAllCharacters(): CommonFlow<List<CharacterModel>> = flow {
        emit(charactersMapper.map(getCharactersInteractor.invoke()))
    }.toCommonFlow()
}