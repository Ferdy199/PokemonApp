package com.ferdsapp.pokemonapp.data.model.elementType

import com.google.gson.annotations.SerializedName

data class ElementTypeResponses(
    @SerializedName("data")
    val data: List<String> = listOf()
)