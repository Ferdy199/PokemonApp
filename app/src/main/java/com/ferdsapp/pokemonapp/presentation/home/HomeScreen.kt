package com.ferdsapp.pokemonapp.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dicoding.jetreward.ui.common.UiState
import com.ferdsapp.pokemonapp.presentation.component.PokemonSearchBar

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    HomeScreenContent(viewModel, modifier = modifier)
}

@Composable
fun HomeScreenContent(
    viewModel: HomeViewModel,
    modifier: Modifier
) {
    LaunchedEffect(Unit) {
        viewModel.getElementType()
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.tertiary
    ) { innerPadding ->
        Box(
            modifier = modifier.padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                PokemonSearchBar()
                Spacer(modifier = Modifier.height(16.dp))
                viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
                    when(uiState) {
                        is UiState.Error -> {
                            Text(
                                text = uiState.errorMessage,
                                fontSize = 12.sp
                            )
                        }
                        is UiState.Loading -> {
                            Text(
                                text = "Loading...",
                                fontSize = 12.sp
                            )
                        }
                        is UiState.Success -> {
                            Text(
                                text = uiState.data.data.toString(),
                                fontSize = 12.sp
                            )
                        }
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreenContent(
        viewModel = hiltViewModel(), modifier = Modifier
    )
}