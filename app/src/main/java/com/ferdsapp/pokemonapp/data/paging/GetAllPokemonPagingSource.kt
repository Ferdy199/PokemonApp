package com.ferdsapp.pokemonapp.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ferdsapp.pokemonapp.data.model.pokemonCard.PokemonCardData
import com.ferdsapp.pokemonapp.data.model.pokemonCard.PokemonCardResponse
import com.ferdsapp.pokemonapp.data.source.IRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllPokemonPagingSource @Inject constructor(
    private val IRemoteDataSource: IRemoteDataSource,
    private val q: String? = null
): PagingSource<Int, PokemonCardData>() {
    override fun getRefreshKey(state: PagingState<Int, PokemonCardData>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonCardData> {
        Log.d("PagingSource", "getAllPokemonCards type: $q")
       return try {
           val page = params.key ?: 1
           val response = IRemoteDataSource.getAllPokemonCards(page, q)

           LoadResult.Page(
               data = response.data,
               prevKey = if (page == 1) null else page.minus(1),
               nextKey = if (page < response.totalCount) page.plus(1) else null
           )
       }catch (e: Exception){
           LoadResult.Error(e)
       }
    }
}