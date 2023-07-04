package com.frakton.rickandmorty.domain.usecases

import com.frakton.rickandmorty.domain.models.CharacterModel
import com.frakton.rickandmorty.domain.repositories.CharactersRepository
import com.frakton.rickandmorty.utils.CommonFlow
import org.koin.core.component.KoinComponent

class GetCharactersUseCase(private val charactersRepository: CharactersRepository) : KoinComponent {
    suspend operator fun invoke(): CommonFlow<List<CharacterModel>> {
        return charactersRepository.getAllCharacters()
    }
}