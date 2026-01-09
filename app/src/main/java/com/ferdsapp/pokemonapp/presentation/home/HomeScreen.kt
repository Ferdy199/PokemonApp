package com.ferdsapp.pokemonapp.presentation.home

import androidx.compose.foundation.clickable
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
import com.ferdsapp.pokemonapp.presentation.component.PokemonCardItemShimmer
import com.ferdsapp.pokemonapp.presentation.component.PokemonSearchBar
import com.ferdsapp.pokemonapp.presentation.component.PokemonTypeItem
import com.ferdsapp.pokemonapp.presentation.component.PokemonTypeItemShimmer

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit
) {
    HomeScreenContent(viewModel, navigateToDetail, modifier = modifier)
}

@Composable
fun HomeScreenContent(
    viewModel: HomeViewModel,
    navigateToDetail: (String) -> Unit,
    modifier: Modifier
) {
    val query by viewModel.query.collectAsState()

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
            PokemonSearchBar(
                query = query,
                onQueryChange = {
                    viewModel.onQueryChanged(it)
                },
                onSearch = {
                    viewModel.searchNow(it)
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
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
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(6) { i ->
                                PokemonTypeItemShimmer(width = 48.dp)
                            }
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
                                        viewModel.getAllPokemonCards("types:$pokemonType")
                                    }
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            val getAllPokemonState = viewModel.getAllCardsUiState.collectAsLazyPagingItems()
            val searchState = viewModel.searchState.collectAsLazyPagingItems()
            val pagingItems = if (query.isBlank()) getAllPokemonState else searchState

            when(pagingItems.loadState.refresh){
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
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxSize()
                            .padding(horizontal = 8.dp)
                    ) {
                        items(8) {
                            PokemonCardItemShimmer()
                        }
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
                    count = pagingItems.itemCount,
                    key = { index ->
                        val item = pagingItems[index]
                        "${item?.id ?: "null"}-$index"
                    }
                ){ index ->
                    val pokemonData = pagingItems[index] ?: return@items
                    PokemonCardItem(
                        imageUrl = pokemonData.images.smallUrl ?: pokemonData.images.largeUrl ?: "",
                        modifier = Modifier.clickable{
                            navigateToDetail(pokemonData.id)
                        }
                    )
                }

                item(span = {GridItemSpan(2)}) {
                    val append = pagingItems.loadState.append
                    when(append){
                        is LoadState.Error -> {
                            Button(
                                onClick = { pagingItems.retry() },
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