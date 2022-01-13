package com.carlinohm.pokeprueba.network.data

import com.carlinohm.pokeprueba.db.entity.Pokemon
import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    @SerializedName("results")
    val results: List<Pokemon>,
) {
}