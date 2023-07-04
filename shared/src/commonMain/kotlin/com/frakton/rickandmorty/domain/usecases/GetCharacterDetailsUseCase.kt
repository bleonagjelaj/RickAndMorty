package com.frakton.rickandmorty.domain.usecases

import com.frakton.rickandmorty.domain.models.CharacterDetailsModel
import com.frakton.rickandmorty.domain.repositories.CharacterDetailsRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent

class GetCharacterDetailsUseCase(
    private val characterDetailsRepository: CharacterDetailsRepository
) : KoinComponent {
    suspend operator fun invoke(characterId: Int): Flow<CharacterDetailsModel> {
        return characterDetailsRepository.getCharacterDetails(characterId)
    }
}