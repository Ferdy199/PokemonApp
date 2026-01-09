package com.ferdsapp.pokemonapp.presentation.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
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

@Composable
fun PokemonTypeItemShimmer(
    modifier: Modifier = Modifier,
    width: Dp = 90.dp
) {
    val transition = rememberInfiniteTransition(label = "typeShimmer")
    val xAnim by transition.animateFloat(
        initialValue = -400f,
        targetValue = 400f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "typeShimmerX"
    )

    val shimmerBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFFE6E6E6),
            Color(0xFFF2F2F2),
            Color(0xFFE6E6E6)
        ),
        start = Offset(xAnim, 0f),
        end = Offset(xAnim + 200f, 200f)
    )

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .width(width)
                .height(34.dp)
                .background(shimmerBrush)
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