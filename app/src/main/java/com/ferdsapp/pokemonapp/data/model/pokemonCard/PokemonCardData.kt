package com.ferdsapp.pokemonapp.data.model.pokemonCard

import com.google.gson.annotations.SerializedName

data class PokemonCardData(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("images")
    val images: PokemonImagesSize
)