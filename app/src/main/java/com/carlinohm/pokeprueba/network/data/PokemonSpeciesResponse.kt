package com.carlinohm.pokeprueba.network.data

import com.google.gson.annotations.SerializedName

data class PokemonSpeciesResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("base_happiness")
    val baseHappiness: Int,
    @SerializedName("capture_rate")
    val captureRate: Int,
    @SerializedName("egg_groups")
    val eggGroups: List<EggGroupResponse>,
    @SerializedName("evolution_chain")
    val evolutionChainUrl: EvolutionChainUrl
) {
    data class EvolutionChainUrl(
        @SerializedName("url")
        val url: String
    )
}