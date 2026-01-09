package com.ferdsapp.pokemonapp.data.model.pokemonDetail

import com.ferdsapp.pokemonapp.data.model.pokemonCard.PokemonImagesSize
import com.google.gson.annotations.SerializedName

data class PokemonDetailData(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String = "",

    @SerializedName("subtypes")
    val subtypes: List<String>? = listOf(),

    @SerializedName("hp")
    val hp: String = "",

    @SerializedName("types")
    val types: List<String> = listOf(),

    @SerializedName("rules")
    val rules: List<String> = listOf(),

    @SerializedName("images")
    val images: List<PokemonImagesSize> = listOf()
)