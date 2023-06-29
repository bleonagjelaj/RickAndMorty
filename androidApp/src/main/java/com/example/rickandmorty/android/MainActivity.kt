package com.example.rickandmorty.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.android.screens.CharacterDetails
import com.example.rickandmorty.android.screens.CharactersList
import com.example.rickandmorty.domain.models.CharacterDetailsModel
import com.example.rickandmorty.domain.models.CharacterModel
import com.example.rickandmorty.viewmodels.CharactersViewModel
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val viewModel: CharactersViewModel by inject()
    private val charactersScreenRouteKey = "charactersScreen"
    private val characterDetailsScreenRouteKey = "characterDetailsScreen"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            var charactersList by remember { mutableStateOf(listOf<CharacterModel>()) }
            var characterDetails by remember {
                mutableStateOf(
                    CharacterDetailsModel(
                        name = "",
                        status = "",
                        species = "",
                        gender = "",
                        origin = "",
                        pictureUrl = ""
                    )
                )
            }

            LaunchedEffect(true) {
                viewModel.getAllCharacters()
                viewModel.characters.observe(this@MainActivity) { characters ->
                    charactersList = characters
                }
                viewModel.characterDetails.observe(this@MainActivity) { characterDetailsModel ->
                    characterDetails = characterDetailsModel
                }
            }

            NavHost(navController, startDestination = "charactersScreen") {
                composable(charactersScreenRouteKey) {
                    CharactersList(
                        itemViewStates = charactersList,
                        onItemClicked = { characterId ->
                            viewModel.getCharacterDetails(characterId)
                            navController.navigate(characterDetailsScreenRouteKey)
                        }
                    )
                }
                composable(characterDetailsScreenRouteKey) { CharacterDetails(characterDetails) }
            }
        }
    }
}