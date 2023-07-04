package com.example.rickandmorty.viewmodels

import com.example.rickandmorty.domain.states.CharactersState
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

class CharactersViewModel(
    coroutineScope: CoroutineScope? = null
) : KoinComponent {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val getCharactersUseCase: GetCharactersUseCase by inject()

    private val _state = MutableStateFlow(CharactersState())
    val state = _state
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = CharactersState()
        )
        .toCommonStateFlow()

    fun getStateUpdate() {
        viewModelScope.launch {
            getCharactersUseCase.invoke().collect { charactersList ->
                _state.update { state ->
                    state.copy(characters = charactersList)
                }
            }
        }
    }
}