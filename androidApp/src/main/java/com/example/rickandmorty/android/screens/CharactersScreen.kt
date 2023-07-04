package com.example.rickandmorty.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.rickandmorty.android.MyApplicationTheme
import com.example.rickandmorty.domain.models.CharacterModel

@Composable
fun CharactersList(
    itemViewStates: List<CharacterModel>,
    onItemClicked: (Int) -> Unit = {}
) {
    val gradientColors = listOf(
        Color(0xFFF5EA90),
        Color(0xFFBCF778)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(gradientColors))
            .padding(10.dp)
    ) {
        Text(
            text = "Rick and Morty",
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            modifier = Modifier.fillMaxWidth()
        )
        LazyColumn(modifier = Modifier.padding(top = 20.dp)) {
            items(itemViewStates) { data ->
                CharacterListItem(itemViewState = data, onItemClicked = onItemClicked)
                Divider(color = Color.Gray)
            }
        }
    }
}

@Composable
fun CharacterListItem(
    itemViewState: CharacterModel,
    onItemClicked: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(6.dp)
            .clickable { onItemClicked(itemViewState.characterId) }
    ) {
        Text(
            text = itemViewState.characterId.toString(),
            fontSize = 18.sp,
            modifier = Modifier.size(25.dp)
        )
        AsyncImage(
            model = itemViewState.pictureUrl,
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .padding(start = 10.dp)
        )
        Text(
            text = itemViewState.name,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        CharactersList(
            itemViewStates = listOf()
        )
    }
}