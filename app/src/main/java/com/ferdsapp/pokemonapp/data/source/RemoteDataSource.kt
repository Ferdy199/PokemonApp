package com.ferdsapp.pokemonapp.data.source

import android.util.Log
import com.ferdsapp.pokemonapp.data.model.elementType.ElementTypeResponses
import com.ferdsapp.pokemonapp.data.model.pokemonCard.PokemonCardResponse
import com.ferdsapp.pokemonapp.data.model.pokemonDetail.PokemonDetailResponses
import com.ferdsapp.pokemonapp.data.network.ApiService
import com.ferdsapp.pokemonapp.data.utils.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor (private val apiService: ApiService): IRemoteDataSource {
    override suspend fun getElementType(): Flow<ApiResponse<ElementTypeResponses>> {
        return flow {
            try {
                val apiResponse = apiService.getElementType()
                if (apiResponse.data.isNotEmpty()){
                    val newResponse = apiResponse.copy(
                        data = listOf("All") + apiResponse.data
                    )
                    emit(ApiResponse.Success(newResponse))
                }else{
                    emit(ApiResponse.Error("Failed Get Api"))
                }

            }catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getAllPokemonCards(page: Int?, q: String?): PokemonCardResponse {
        return try {
            Log.d("RemoteDataSource", "getAllPokemonCards type: $q")
            var query = q
            if (query != null) {
                if (query.lowercase().contains("all")){
                    query = null
                }
            }

            val response = apiService.getAllPokemonCards(
                q = if (query.isNullOrEmpty()) null else query,
                page = page ?: 1,
                pageSize = 8)
            response
        }catch (e: Exception){
            throw e
        }
    }

    override fun getPokemonDetailData(id: String): Flow<ApiResponse<PokemonDetailResponses>> {
        return flow {
            try {
                val apiResponse = apiService.getPokemonDetail(id)
                if (apiResponse.data != null){
                    emit(ApiResponse.Success(apiResponse))
                }else{
                    emit(ApiResponse.Error("Failed get data"))
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }
    }

}