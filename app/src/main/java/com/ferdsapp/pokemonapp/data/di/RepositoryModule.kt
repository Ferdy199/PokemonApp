package com.ferdsapp.pokemonapp.data.di

import com.ferdsapp.pokemonapp.data.repository.PokemonRepository
import com.ferdsapp.pokemonapp.domain.repository.IPokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(pokemonRepository: PokemonRepository): IPokemonRepository
}