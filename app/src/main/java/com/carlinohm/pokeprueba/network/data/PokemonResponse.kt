package com.carlinohm.pokeprueba.network.data

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("abilities")
    val abilities: List<AbilityListResponse>
)