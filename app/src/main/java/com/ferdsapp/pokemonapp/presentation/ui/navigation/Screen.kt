package com.ferdsapp.pokemonapp.presentation.ui.navigation

sealed class Screen(val route: String){
    data object Home: Screen("home")
    data object Detail: Screen("home/{id}"){
        fun createRoute(id: String) = "home/$id"
    }
}