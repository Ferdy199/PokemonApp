package com.ferdsapp.pokemonapp.data.network

import com.ferdsapp.pokemonapp.data.model.elementType.ElementTypeResponses
import retrofit2.http.GET

interface ApiService {
    @GET("v2/types")
    suspend fun getElementType(): ElementTypeResponses
}