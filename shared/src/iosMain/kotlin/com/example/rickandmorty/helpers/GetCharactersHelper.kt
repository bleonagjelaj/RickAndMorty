package com.example.rickandmorty.helpers

import com.example.rickandmorty.domain.models.CharacterModel
import com.example.rickandmorty.domain.usecases.GetCharactersUseCase
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetCharactersHelper : KoinComponent {
    private val getCharactersUseCase: GetCharactersUseCase by inject()

    suspend fun getCharacters(): Flow<List<CharacterModel>> = getCharactersUseCase.invoke()
}