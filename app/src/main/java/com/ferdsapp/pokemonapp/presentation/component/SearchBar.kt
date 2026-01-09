package com.ferdsapp.pokemonapp.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ferdsapp.pokemonapp.presentation.ui.theme.PokemonAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = onSearch,
                expanded = false,
                onExpandedChange = {},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                    )
                },
                placeholder = {
                    Text(
                        text = "Find The Pokemon"
                    )
                }
            )
        },
        expanded = false,
        onExpandedChange = {},
        shape = MaterialTheme.shapes.large,
        modifier = modifier.padding(8.dp)
            .fillMaxWidth(),
    ){}
}