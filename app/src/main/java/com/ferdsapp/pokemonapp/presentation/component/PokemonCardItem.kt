package com.ferdsapp.pokemonapp.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ferdsapp.pokemonapp.presentation.ui.theme.PokemonAppTheme

@Composable
fun PokemonCardItem(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(5f / 7f)
    ) {
        Box {
            AsyncImage(
                model = imageUrl,
                contentScale = ContentScale.Fit,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview
@Composable
private fun PokemonCardPreview() {
    PokemonAppTheme {
        PokemonCardItem(
            imageUrl = "https://images.pokemontcg.io/hgss4/1.png"
        )
    }
}