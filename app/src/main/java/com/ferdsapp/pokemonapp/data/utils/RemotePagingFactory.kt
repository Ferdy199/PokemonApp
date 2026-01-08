package com.ferdsapp.home.di

import com.ferdsapp.pokemonapp.data.paging.GetAllPokemonPagingSource


fun interface RemotePagingFactory {
    fun create(q: String?): GetAllPokemonPagingSource
}