package com.carlinohm.pokeprueba.network.data

import com.carlinohm.pokeprueba.db.entity.Pokemon
import com.google.gson.annotations.SerializedName

data class EvolutionChainResponse(
    @SerializedName("chain")
    val chain: EvolutionResponse
) {
    data class EvolutionResponse(
        @SerializedName("evolves_to")
        val evolvesTo: List<EvolutionResponse>,
        @SerializedName("species")
        val species: Pokemon
    ) {
        fun preOrder(list: MutableList<Pokemon>) {
            list.add(species)
            try {
                for (element in evolvesTo) {
                    element.preOrder(list)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}