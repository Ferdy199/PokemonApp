package com.ferdsapp.pokemonapp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ferdsapp.pokemonapp.presentation.component.PokemonSearchBar
import com.ferdsapp.pokemonapp.presentation.home.HomeScreen
import com.ferdsapp.pokemonapp.presentation.home.HomeScreenContent
import com.ferdsapp.pokemonapp.presentation.ui.theme.PokemonAppTheme

@Composable
fun PokemonApp(
    modifier: Modifier = Modifier
) {
    PokemonAppContent()
}

@Composable
fun PokemonAppContent(modifier: Modifier = Modifier) {
    HomeScreen()
}

@Preview(showBackground = true)
@Composable
private fun PokemonAppPreview() {
    PokemonAppTheme {
        PokemonAppContent()
    }
}