package com.carlinohm.pokeprueba.repository

import androidx.annotation.WorkerThread
import com.carlinohm.pokeprueba.db.dao.PokemonDao
import com.carlinohm.pokeprueba.db.entity.Pokemon
import com.carlinohm.pokeprueba.network.PokemonService
import com.carlinohm.pokeprueba.network.data.EvolutionChainResponse
import kotlinx.coroutines.flow.Flow
import kotlin.random.Random

class PokemonRepository(private val pokemonDao: PokemonDao) {
    val api = PokemonService()

    val allPokemons: Flow<List<Pokemon>> = pokemonDao.getAllPokemons()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(list: List<Pokemon>) {
        pokemonDao.insert(list)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(pokemon: Pokemon) {
        pokemonDao.update(pokemon)
    }

    suspend fun getPokemons(limit: Int) {
        val response = api.getPokemons(limit)
        insert(response)
    }

    suspend fun getEvolutionChain(url: String): EvolutionChainResponse {
        return api.getEvolutionChain(url)
    }

    fun setFavorite(): Boolean {
        return Random.nextBoolean()
    }

}