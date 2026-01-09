package com.ferdsapp.pokemonapp.data.di

import com.ferdsapp.home.di.RemotePagingFactory
import com.ferdsapp.pokemonapp.data.paging.GetAllPokemonPagingSource
import com.ferdsapp.pokemonapp.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PagingModule {
    @Provides
    @Singleton
    fun providePagingFactory(
        remoteDataSource: RemoteDataSource
    ): RemotePagingFactory = RemotePagingFactory(remoteDataSource = remoteDataSource)
}