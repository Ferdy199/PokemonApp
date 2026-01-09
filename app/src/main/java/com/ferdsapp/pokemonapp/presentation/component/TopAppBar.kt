package com.ferdsapp.pokemonapp.presentation.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ferdsapp.pokemonapp.presentation.ui.theme.PokemonAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Text(
                text = "Pokedex",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    )
}

@Preview
@Composable
private fun PokemonAppBarPreview() {
    PokemonAppTheme {
        PokemonAppBar()
    }
}