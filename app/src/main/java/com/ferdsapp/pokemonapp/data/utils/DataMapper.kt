package com.ferdsapp.pokemonapp.data.utils

import com.ferdsapp.pokemonapp.data.model.elementType.ElementTypeResponses
import com.ferdsapp.pokemonapp.domain.model.ElementTypeEntity

object DataMapper {
    fun mapElementTypeResponsesToDomain(input: ElementTypeResponses): ElementTypeEntity{
        return ElementTypeEntity(
            data = input.data
        )
    }
}