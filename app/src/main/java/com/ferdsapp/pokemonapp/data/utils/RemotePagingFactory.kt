package com.ferdsapp.home.di

import com.ferdsapp.pokemonapp.data.paging.GetAllPokemonPagingSource
import com.ferdsapp.pokemonapp.data.source.IRemoteDataSource
import javax.inject.Inject


class RemotePagingFactory @Inject constructor(
    private val remoteDataSource: IRemoteDataSource
) {
    fun create(q: String?) = GetAllPokemonPagingSource(remoteDataSource, q = q)
}