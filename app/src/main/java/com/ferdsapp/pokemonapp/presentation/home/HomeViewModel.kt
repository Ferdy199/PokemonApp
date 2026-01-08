package com.ferdsapp.pokemonapp.presentation.home

import androidx.lifecycle.ViewModel
import com.ferdsapp.pokemonapp.domain.useCase.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val pokemonUseCase: PokemonUseCase): ViewModel() {
}