package com.ferdsapp.pokemonapp.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.dicoding.jetreward.ui.common.UiState
import com.ferdsapp.home.di.RemotePagingFactory
import com.ferdsapp.pokemonapp.data.source.IRemoteDataSource
import com.ferdsapp.pokemonapp.data.utils.ApiResponse
import com.ferdsapp.pokemonapp.data.utils.DataMapper.mapElementTypeResponsesToDomain
import com.ferdsapp.pokemonapp.data.utils.DataMapper.mapPokemonCardDataToDomain
import com.ferdsapp.pokemonapp.data.utils.DataMapper.mapPokemonDetailDataToDomain
import com.ferdsapp.pokemonapp.domain.model.ElementTypeEntity
import com.ferdsapp.pokemonapp.domain.model.PokemonCardEntity
import com.ferdsapp.pokemonapp.domain.model.PokemonDetailDataEntity
import com.ferdsapp.pokemonapp.domain.repository.IPokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepository @Inject constructor (
    private val remotePagingFactory: RemotePagingFactory,
    private val pokemonDataSource: IRemoteDataSource
): IPokemonRepository {
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
                           val getElementResponse = apiResponse.data.mapElementTypeResponsesToDomain(apiResponse.data)
                           emit(UiState.Success(getElementResponse))
                       }
                   }
               }
           }catch (e: Exception){
               emit(UiState.Error(e.message.toString()))
           }
       }
    }

    override suspend fun getAllPokemonCards(q: String?): Flow<PagingData<PokemonCardEntity>> {
        Log.d("Repository", "getAllPokemonCards type: $q")
       return Pager(
           config = PagingConfig(
               pageSize = 8,
               prefetchDistance = 5,
               enablePlaceholders = false
           ),
           pagingSourceFactory = {
               remotePagingFactory.create(q)
           }
       ).flow
           .map { pagingData ->
               pagingData.map { data ->
                   data.mapPokemonCardDataToDomain(data)
               }
           }
    }

    override fun getPokemonDetailData(id: String): Flow<UiState<PokemonDetailDataEntity>> {
        return flow {
            try {
                pokemonDataSource.getPokemonDetailData(id).collect { detailResponse ->
                    when(detailResponse){
                        is ApiResponse.Error -> emit(UiState.Error(detailResponse.message.toString()))
                        is ApiResponse.Loading -> emit(UiState.Loading)
                        is ApiResponse.Success -> {
                            val responseDetail = detailResponse.data.data
                            val mapDetailResponse = responseDetail?.mapPokemonDetailDataToDomain(responseDetail)
                            if (mapDetailResponse != null){
                                emit(UiState.Success(mapDetailResponse))
                            }else{
                                emit(UiState.Error("Failed Get Detail"))
                            }
                        }
                    }
                }
            }catch (e: Exception){
                emit(UiState.Error(e.message.toString()))
            }
        }
    }
}