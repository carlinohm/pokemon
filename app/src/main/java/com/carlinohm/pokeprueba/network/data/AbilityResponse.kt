package com.carlinohm.pokeprueba.network.data

import com.google.gson.annotations.SerializedName

data class AbilityResponse(
    @SerializedName("name")
    val name: String
)