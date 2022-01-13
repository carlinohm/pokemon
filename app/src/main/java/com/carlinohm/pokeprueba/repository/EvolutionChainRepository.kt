package com.carlinohm.pokeprueba.repository

import com.carlinohm.pokeprueba.network.PokemonService
import com.carlinohm.pokeprueba.network.data.EvolutionChainResponse

class EvolutionChainRepository() {
    val api = PokemonService()

    suspend fun getEvolutionChain(url: String): EvolutionChainResponse {
        return api.getEvolutionChain(url)
    }

}