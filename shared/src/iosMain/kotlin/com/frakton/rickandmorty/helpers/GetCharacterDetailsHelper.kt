package com.frakton.rickandmorty.helpers

import com.frakton.rickandmorty.domain.models.CharacterDetailsModel
import com.frakton.rickandmorty.domain.usecases.GetCharacterDetailsUseCase
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetCharacterDetailsHelper : KoinComponent {
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase by inject()

    suspend fun getCharacterDetails(characterId: Int): Flow<CharacterDetailsModel> =
        getCharacterDetailsUseCase.invoke(characterId)
}