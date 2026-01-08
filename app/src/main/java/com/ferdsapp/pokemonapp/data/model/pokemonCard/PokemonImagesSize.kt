package com.ferdsapp.pokemonapp.data.model.pokemonCard

import com.google.gson.annotations.SerializedName

data class PokemonImagesSize(
    @SerializedName("small")
    val smallUrl: String? = "",

    @SerializedName("large")
    val largeUrl: String? = ""
)