package com.ferdsapp.pokemonapp.data.model.pokemonDetail

import com.google.gson.annotations.SerializedName

data class PokemonDetailResponses(
    @SerializedName("data")
    val data: PokemonDetailData? = null,
)