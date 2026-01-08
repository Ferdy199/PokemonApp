package com.ferdsapp.pokemonapp.data.utils

sealed class ApiResponse <out T> {
    data object Loading : ApiResponse<Nothing>()

    data class Success<out T>(val data: T) : ApiResponse<T>()

    data class Error(
        val message: String? = null,
        val throwable: Throwable? = null
    ) : ApiResponse<Nothing>()
}