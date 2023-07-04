package com.example.rickandmorty.viewmodels

import com.example.rickandmorty.domain.states.CharacterDetailsState
import com.example.rickandmorty.domain.states.CharactersState
import com.example.rickandmorty.domain.usecases.GetCharacterDetailsUseCase
import com.example.rickandmorty.domain.usecases.GetCharactersUseCase
import com.example.rickandmorty.utils.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharacterDetailsViewModel (
    coroutineScope: CoroutineScope? = null
): KoinComponent {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase by inject()

    private val _state = MutableStateFlow(CharacterDetailsState())
    val state = _state
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = CharacterDetailsState()
        )
        .toCommonStateFlow()

    fun getStateUpdate(characterId: Int) {
        viewModelScope.launch {
            getCharacterDetailsUseCase.invoke(characterId).collect { characterDetailsModel ->
                _state.update { state ->
                    state.copy(characterDetails = characterDetailsModel)
                }
            }
        }
    }
}