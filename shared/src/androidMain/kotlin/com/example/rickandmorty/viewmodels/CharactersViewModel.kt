package com.example.rickandmorty.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.models.CharacterDetailsModel
import com.example.rickandmorty.domain.models.CharacterModel
import com.example.rickandmorty.domain.usecases.GetCharacterDetailsUseCase
import com.example.rickandmorty.domain.usecases.GetCharactersUseCase
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase
) : ViewModel(), KoinComponent {
    //for characters list
    private var _characters = MutableLiveData<List<CharacterModel>>()
    val characters: MutableLiveData<List<CharacterModel>> = _characters

    fun getAllCharacters() {
        viewModelScope.launch {
            getCharactersUseCase.invoke().collect { charactersList ->
                _characters.value = charactersList
            }
        }
    }

    //for character details
    private var _characterDetails = MutableLiveData<CharacterDetailsModel>()
    val characterDetails: MutableLiveData<CharacterDetailsModel> = _characterDetails

    fun getCharacterDetails(id: Int) {
        viewModelScope.launch {
            getCharacterDetailsUseCase.invoke(id).collect { characterDetailsModel ->
                characterDetails.value = characterDetailsModel
            }
        }
    }
}