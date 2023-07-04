package com.example.rickandmorty.domain.usecases

import com.example.rickandmorty.domain.models.CharacterModel
import com.example.rickandmorty.domain.repositories.CharactersRepository
import com.example.rickandmorty.utils.CommonFlow
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent

class GetCharactersUseCase(private val charactersRepository: CharactersRepository) : KoinComponent {
    suspend operator fun invoke(): CommonFlow<List<CharacterModel>> {
        return charactersRepository.getAllCharacters()
    }
}