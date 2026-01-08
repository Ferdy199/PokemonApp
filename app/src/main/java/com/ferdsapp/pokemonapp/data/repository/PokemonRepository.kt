package com.ferdsapp.pokemonapp.data.repository

import com.dicoding.jetreward.ui.common.UiState
import com.ferdsapp.pokemonapp.data.source.IRemoteDataSource
import com.ferdsapp.pokemonapp.data.utils.ApiResponse
import com.ferdsapp.pokemonapp.data.utils.DataMapper
import com.ferdsapp.pokemonapp.domain.model.ElementTypeEntity
import com.ferdsapp.pokemonapp.domain.repository.IPokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepository @Inject constructor (private val pokemonDataSource: IRemoteDataSource): IPokemonRepository {
    override suspend fun getElementType(): Flow<UiState<ElementTypeEntity>> {
       return flow {
           try {
               pokemonDataSource.getElementType().collect { apiResponse ->
                   when(apiResponse) {
                       is ApiResponse.Error -> {
                           emit(UiState.Error(apiResponse.message.toString()))
                       }
                       is ApiResponse.Loading -> {
                           emit(UiState.Loading)
                       }
                       is ApiResponse.Success -> {
                           val getElementResponse = DataMapper.mapElementTypeResponsesToDomain(apiResponse.data)
                           emit(UiState.Success(getElementResponse))
                       }
                   }
               }
           }catch (e: Exception){
               emit(UiState.Error(e.message.toString()))
           }
       }
    }
}