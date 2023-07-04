package com.frakton.rickandmorty.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.frakton.rickandmorty.domain.models.CharacterDetailsModel

@Composable
fun CharacterDetails(
    characterDetailsModel: CharacterDetailsModel
) {
    val gradientColors = listOf(
        Color(0xFFF5EA90),
        Color(0xFFBCF778)
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(gradientColors))
    ) {
        AsyncImage(
            model = characterDetailsModel.pictureUrl,
            contentDescription = null,
            modifier = Modifier.size(250.dp)
        )
        Text(
            text = characterDetailsModel.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            text = "Status: ${characterDetailsModel.status}",
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            text = "Gender: ${characterDetailsModel.gender}",
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 6.dp)
        )
        Text(
            text = "Species: ${characterDetailsModel.species}",
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 6.dp)
        )
        Text(
            text = "Origin: ${characterDetailsModel.origin}",
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 6.dp)
        )
    }
}
