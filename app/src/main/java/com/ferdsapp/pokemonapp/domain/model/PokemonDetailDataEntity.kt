package com.ferdsapp.pokemonapp.domain.model

import com.ferdsapp.pokemonapp.data.model.pokemonCard.PokemonImagesSize

data class PokemonDetailDataEntity (

    val id: String,

    val name: String = "",

    val subtypes: List<String>? = listOf(),

    val hp: String = "",

    val types: List<String> = listOf(),

    val rules: List<String> = listOf(),

    val images: List<PokemonImagesSize> = listOf()
)