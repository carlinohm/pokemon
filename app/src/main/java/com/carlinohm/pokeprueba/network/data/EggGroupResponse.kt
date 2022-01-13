package com.carlinohm.pokeprueba.network.data

import com.google.gson.annotations.SerializedName

data class EggGroupResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
){
    override fun toString(): String {
        return name
    }
}