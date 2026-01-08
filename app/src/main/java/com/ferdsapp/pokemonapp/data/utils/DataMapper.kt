package com.ferdsapp.pokemonapp.data.utils

import com.ferdsapp.pokemonapp.data.model.elementType.ElementTypeResponses
import com.ferdsapp.pokemonapp.data.model.pokemonCard.PokemonCardData
import com.ferdsapp.pokemonapp.domain.model.ElementTypeEntity
import com.ferdsapp.pokemonapp.domain.model.PokemonCardEntity

object DataMapper {
    fun ElementTypeResponses.mapElementTypeResponsesToDomain(input: ElementTypeResponses): ElementTypeEntity{
        return ElementTypeEntity(
            data = input.data
        )
    }

    fun PokemonCardData.mapPokemonCardDataToDomain(input: PokemonCardData): PokemonCardEntity{
        return PokemonCardEntity(
            id = input.id,
            name = input.name,
            images = input.images
        )
    }
}