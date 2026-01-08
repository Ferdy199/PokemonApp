package com.ferdsapp.pokemonapp.data.di

import com.ferdsapp.pokemonapp.data.source.IRemoteDataSource
import com.ferdsapp.pokemonapp.data.source.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun providesRemoteDatasource(
        remoteDataSource: RemoteDataSource
    ): IRemoteDataSource
}