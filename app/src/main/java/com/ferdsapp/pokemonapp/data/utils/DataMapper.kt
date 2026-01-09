package com.ferdsapp.pokemonapp.data.utils

import com.ferdsapp.pokemonapp.data.model.elementType.ElementTypeResponses
import com.ferdsapp.pokemonapp.data.model.pokemonCard.PokemonCardData
import com.ferdsapp.pokemonapp.data.model.pokemonCard.PokemonImagesSize
import com.ferdsapp.pokemonapp.data.model.pokemonDetail.PokemonDetailData
import com.ferdsapp.pokemonapp.domain.model.ElementTypeEntity
import com.ferdsapp.pokemonapp.domain.model.PokemonCardEntity
import com.ferdsapp.pokemonapp.domain.model.PokemonDetailDataEntity
import kotlin.String

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

    fun PokemonDetailData.mapPokemonDetailDataToDomain(input: PokemonDetailData): PokemonDetailDataEntity{
        return PokemonDetailDataEntity(
            id = input.id,
            name = input.name,
            subtypes = input.subtypes,
            hp = input.hp,
            types = input.types,
            rules = input.rules,
            images = input.images
        )
    }
}