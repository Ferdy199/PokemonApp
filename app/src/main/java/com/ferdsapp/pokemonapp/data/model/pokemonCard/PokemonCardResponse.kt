package com.ferdsapp.pokemonapp.data.model.pokemonCard

import com.google.gson.annotations.SerializedName

data class PokemonCardResponse(
    @SerializedName("data")
    val data: List<PokemonCardData> = listOf(),

    @SerializedName("totalCount")
    val totalCount: Int = 0
)