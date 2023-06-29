package com.example.rickandmorty.domain.repositories

import com.example.rickandmorty.domain.interactors.GetCharacterDetailsInteractor
import com.example.rickandmorty.domain.mappers.CharacterDetailsMapper
import com.example.rickandmorty.domain.models.CharacterDetailsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent

class CharacterDetailsRepository(
    private val getCharacterDetailsInteractor: GetCharacterDetailsInteractor,
    private val characterDetailsMapper: CharacterDetailsMapper
) : KoinComponent {
    suspend fun getCharacterDetails(characterId: Int): Flow<CharacterDetailsModel> = flow {
        emit(
            characterDetailsMapper.map(
                getCharacterDetailsInteractor.invoke(characterId)
            )
        )
    }
}