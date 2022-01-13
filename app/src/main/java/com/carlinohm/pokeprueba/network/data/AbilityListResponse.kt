package com.carlinohm.pokeprueba.network.data

import com.google.gson.annotations.SerializedName

data class AbilityListResponse(
    @SerializedName("ability")
    val ability: AbilityResponse,
    @SerializedName("is_hidden")
    val isHidden: Boolean
)