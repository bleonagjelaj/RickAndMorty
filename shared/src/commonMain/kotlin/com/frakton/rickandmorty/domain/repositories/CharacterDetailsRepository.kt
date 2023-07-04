package com.frakton.rickandmorty.domain.repositories

import com.frakton.rickandmorty.domain.interactors.GetCharacterDetailsInteractor
import com.frakton.rickandmorty.domain.mappers.CharacterDetailsMapper
import com.frakton.rickandmorty.domain.models.CharacterDetailsModel
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