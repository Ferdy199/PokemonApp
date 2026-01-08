package com.ferdsapp.pokemonapp.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.dicoding.jetreward.ui.common.UiState
import com.ferdsapp.pokemonapp.presentation.component.PokemonCardItem
import com.ferdsapp.pokemonapp.presentation.component.PokemonSearchBar
import com.ferdsapp.pokemonapp.presentation.component.PokemonTypeItem

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
        viewModel.getAllPokemonCards()
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            PokemonSearchBar()
            Spacer(modifier = Modifier.height(16.dp))
            val getAllPokemonState = viewModel.getAllCardsUiState.collectAsLazyPagingItems()

            viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
                when(uiState) {
                    is UiState.Error -> {
                        Button(
                            onClick = { viewModel.getElementType() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text("Retry Fetch Type")
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
                        var selected by rememberSaveable {
                            mutableStateOf(uiState.data.data.first())
                        }

                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(
                                uiState.data.data,
                                key = {it}
                            ){ pokemonType ->
                                val isSelected = pokemonType == selected

                                PokemonTypeItem(
                                    isSelected = isSelected,
                                    label = pokemonType,
                                    onClick = {
                                        selected = pokemonType
                                    }
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            when(val state = getAllPokemonState.loadState.refresh){
                is LoadState.Error -> {
                    Button(
                        onClick = { getAllPokemonState.retry() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text("Retry Fetch Pokemon")
                    }
                }
                is LoadState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is LoadState.NotLoading -> Unit
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    count = getAllPokemonState.itemCount,
                    key = { index ->
                        val item = getAllPokemonState[index]
                        "${item?.id ?: "null"}-$index"
                    }
                ){ index ->
                    val pokemonData = getAllPokemonState[index] ?: return@items
                    PokemonCardItem(
                        imageUrl = pokemonData.images.smallUrl ?: pokemonData.images.largeUrl ?: ""
                    )
                }

                item(span = {GridItemSpan(2)}) {
                    val append = getAllPokemonState.loadState.append
                    when(append){
                        is LoadState.Error -> {
                            Button(
                                onClick = { getAllPokemonState.retry() },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text("Retry")
                            }
                        }
                        is LoadState.Loading -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                        is LoadState.NotLoading -> Unit
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