package com.ferdsapp.pokemonapp.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ferdsapp.pokemonapp.presentation.component.PokemonAppBar
import com.ferdsapp.pokemonapp.presentation.home.HomeScreen
import com.ferdsapp.pokemonapp.presentation.ui.navigation.Screen
import com.ferdsapp.pokemonapp.presentation.ui.theme.PokemonAppTheme
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ferdsapp.pokemonapp.presentation.detail.DetailScreen

@Composable
fun PokemonApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    PokemonAppContent(
        navController,
        modifier
    )
}

@Composable
fun PokemonAppContent(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            PokemonAppBar()
        },
        containerColor = Color(0xFFFFF7F3),
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding),
        ){
            composable(Screen.Home.route){
                HomeScreen(
                    modifier = modifier,
                    navigateToDetail = { id ->
                        navController.navigate(Screen.Detail.createRoute(id))
                    }
                )
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("id"){
                    type = NavType.StringType
                })
            ) {
                val id = it.arguments?.getString("id") ?: ""
                DetailScreen(
                    id = id
                )
            }
        }

    }

}