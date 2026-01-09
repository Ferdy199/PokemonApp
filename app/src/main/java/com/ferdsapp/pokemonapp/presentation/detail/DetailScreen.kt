package com.ferdsapp.pokemonapp.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dicoding.jetreward.ui.common.UiState
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
                    Text("Retry Fetch Type")
                }
            }
            is UiState.Loading -> {
                CircularProgressIndicator()
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
    Box {
        Text(
            text = detailData.toString()
        )
    }
}