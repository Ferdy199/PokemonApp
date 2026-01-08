package com.ferdsapp.pokemonapp.domain.model

import com.ferdsapp.pokemonapp.data.model.pokemonCard.PokemonCardData
import com.ferdsapp.pokemonapp.data.model.pokemonCard.PokemonImagesSize
import com.google.gson.annotations.SerializedName

data class PokemonCardEntity(
    val id: String,
    val name: String,
    val images: PokemonImagesSize
)