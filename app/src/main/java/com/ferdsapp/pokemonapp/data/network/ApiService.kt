package com.ferdsapp.pokemonapp.data.network

import com.ferdsapp.pokemonapp.data.model.elementType.ElementTypeResponses
import com.ferdsapp.pokemonapp.data.model.pokemonCard.PokemonCardResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/types")
    suspend fun getElementType(): ElementTypeResponses

    @GET("v2/cards")
    suspend fun getAllPokemonCards(
        @Query("page")
        page: Int? = 1,

        @Query("pageSize")
        pageSize: Int? = 1

    ): PokemonCardResponse
}