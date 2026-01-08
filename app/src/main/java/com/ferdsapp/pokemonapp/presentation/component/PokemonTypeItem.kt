package com.ferdsapp.pokemonapp.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ferdsapp.pokemonapp.presentation.ui.theme.PokemonAppTheme

@Composable
fun PokemonTypeItem(
    isSelected: Boolean,
    label: String,
    onClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        onClick = {
            onClick(label)
        },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color(0xFF0F172A) else Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 0.dp else 2.dp
        )
    ) {
        Text(
            text = label,
            modifier = modifier.padding(horizontal = 18.dp, vertical = 10.dp),
            color = if (isSelected) Color.White else Color(0xFF111827),
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
private fun PokemonTypeItemPreview() {
    PokemonAppTheme {
        PokemonTypeItem(
            isSelected = false,
            label = "Fire",
        )
    }
}