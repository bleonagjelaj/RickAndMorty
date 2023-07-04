package com.frakton.rickandmorty.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frakton.rickandmorty.domain.models.CharacterDetailsModel
import com.frakton.rickandmorty.domain.models.CharacterModel
import com.frakton.rickandmorty.domain.usecases.GetCharacterDetailsUseCase
import com.frakton.rickandmorty.domain.usecases.GetCharactersUseCase
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class AndroidCharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase
) : ViewModel(), KoinComponent {
    //for characters list
    private var _characters = MutableLiveData<List<CharacterModel>>()
    val characters: MutableLiveData<List<CharacterModel>> = _characters
    private val charactersVM: CharactersViewModel = CharactersViewModel(viewModelScope)
    private val characterDetailsVM: CharacterDetailsViewModel =
        CharacterDetailsViewModel(viewModelScope)

    fun getAllCharacters() {
        charactersVM.getStateUpdate()
        viewModelScope.launch {
            charactersVM.state.collect { charactersState ->
                _characters.value = charactersState.characters
            }
        }
    }

    //for character details
    private var _characterDetails = MutableLiveData<CharacterDetailsModel>()
    val characterDetails: MutableLiveData<CharacterDetailsModel> = _characterDetails

    fun getCharacterDetails(characterId: Int) {
        characterDetailsVM.getStateUpdate(characterId)
        viewModelScope.launch {
            characterDetailsVM.state.collect { characterDetailsState ->
                characterDetails.value = characterDetailsState.characterDetails
            }
        }
    }
}