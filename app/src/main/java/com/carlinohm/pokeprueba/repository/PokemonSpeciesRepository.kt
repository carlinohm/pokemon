package com.carlinohm.pokeprueba.repository

import com.carlinohm.pokeprueba.network.PokemonService
import com.carlinohm.pokeprueba.network.data.PokemonSpeciesResponse

class PokemonSpeciesRepository() {
    val api = PokemonService()

    suspend fun getSpeciesInfo(name: String): PokemonSpeciesResponse {
        return api.getSpiecesInfo(name)
    }

}