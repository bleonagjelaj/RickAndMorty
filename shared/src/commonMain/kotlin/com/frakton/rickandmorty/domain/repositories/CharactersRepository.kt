package com.frakton.rickandmorty.domain.repositories

import com.frakton.rickandmorty.domain.interactors.GetCharactersInteractor
import com.frakton.rickandmorty.domain.mappers.CharactersMapper
import com.frakton.rickandmorty.domain.models.CharacterModel
import com.frakton.rickandmorty.utils.CommonFlow
import com.frakton.rickandmorty.utils.toCommonFlow
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