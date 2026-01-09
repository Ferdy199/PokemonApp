package com.ferdsapp.pokemonapp.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.dicoding.jetreward.ui.common.UiState
import com.ferdsapp.pokemonapp.data.model.pokemonCard.PokemonImagesSize
import com.ferdsapp.pokemonapp.domain.model.PokemonDetailDataEntity
import com.ferdsapp.pokemonapp.presentation.ui.theme.PokemonAppTheme

@Composable
fun DetailScreen(
    id: String?,
    viewModel: DetailViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        viewModel.getPokemonDetailData(id ?: "")
    }
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Error -> {
                Button(
                    onClick = { viewModel.getPokemonDetailData(id ?: "") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Retry Fetch Detail")
                }
            }
            is UiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is UiState.Success -> {
                val detailData = uiState.data
                DetailScreenContent(
                    detailData
                )
            }
        }
    }
}

@Composable
fun DetailScreenContent(
    detailData: PokemonDetailDataEntity,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            AsyncImage(
                model = detailData.images.smallUrl,
                contentScale = ContentScale.Fit,
                contentDescription = null,
                modifier = Modifier
                    .height(260.dp)
                    .aspectRatio(0.72f)
                    .clip(RoundedCornerShape(14.dp))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    PokemonAppTheme {
        DetailScreenContent(
            detailData = PokemonDetailDataEntity(
                id = "",
                name = "",
                subtypes = listOf(),
                hp = "",
                types = listOf(),
                rules = listOf(),
                images = PokemonImagesSize()
            )
        )
    }
}
