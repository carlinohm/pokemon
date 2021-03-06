package com.carlinohm.pokeprueba.repository

import com.carlinohm.pokeprueba.network.PokemonService
import com.carlinohm.pokeprueba.network.data.AbilityListResponse

class AbilityRepository {

    val api = PokemonService()

    suspend fun getPokemonAbilities(name: String): List<AbilityListResponse> {
        return api.getPokemonAbilities(name)
    }
}