package com.carlinohm.pokeprueba.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "pokemon_table")
class Pokemon(
    @PrimaryKey
    @ColumnInfo(name = "name")
    @SerializedName("name") val name: String,
    @ColumnInfo(name = "fav")
    var fav: Int = 0
) {
}