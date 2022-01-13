package com.carlinohm.pokeprueba.network

import com.carlinohm.pokeprueba.network.data.EvolutionChainResponse
import com.carlinohm.pokeprueba.network.data.PokemonListResponse
import com.carlinohm.pokeprueba.network.data.PokemonResponse
import com.carlinohm.pokeprueba.network.data.PokemonSpeciesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonClient {
    @GET("pokemon")
    suspend fun getPokemons(@Query("limit") limit: Int): PokemonListResponse

    @GET("pokemon-species/{name}/")
    suspend fun getSpeciesInfo(@Path("name") name: String): PokemonSpeciesResponse

    @GET
    suspend fun getEvolutionChain(@Url url: String): EvolutionChainResponse

    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): PokemonResponse
}