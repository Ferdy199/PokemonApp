package com.ferdsapp.pokemonapp.data.di

import com.ferdsapp.pokemonapp.domain.useCase.PokemonInteractor
import com.ferdsapp.pokemonapp.domain.useCase.PokemonUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun providePokemonUseCase(pokemonInteractor: PokemonInteractor): PokemonUseCase
}