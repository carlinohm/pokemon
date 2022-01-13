package com.carlinohm.pokeprueba.db.dao

import androidx.room.*
import com.carlinohm.pokeprueba.db.entity.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon_table")
    fun getAllPokemons(): Flow<List<Pokemon>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(list: List<Pokemon>)

    @Update
    suspend fun update(pokemon: Pokemon)

}