package com.carlinohm.pokeprueba.network

import com.carlinohm.pokeprueba.db.entity.Pokemon
import com.carlinohm.pokeprueba.network.data.AbilityListResponse
import com.carlinohm.pokeprueba.network.data.EvolutionChainResponse
import com.carlinohm.pokeprueba.network.data.PokemonSpeciesResponse
import com.carlinohm.pokeprueba.network.retrofit.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getPokemons(limit: Int): List<Pokemon> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(PokemonClient::class.java).getPokemons(limit)
            response.results
        }
    }

    suspend fun getSpiecesInfo(name: String): PokemonSpeciesResponse {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(PokemonClient::class.java).getSpeciesInfo(name)
            response
        }
    }

    suspend fun getEvolutionChain(url: String): EvolutionChainResponse {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(PokemonClient::class.java).getEvolutionChain(url)
            response
        }
    }

    suspend fun getPokemonAbilities(name: String): List<AbilityListResponse> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(PokemonClient::class.java).getPokemon(name)
            response.abilities
        }
    }
}