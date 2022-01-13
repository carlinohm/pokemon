package com.carlinohm.pokeprueba.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.carlinohm.pokeprueba.db.dao.PokemonDao
import com.carlinohm.pokeprueba.db.entity.Pokemon
import com.carlinohm.pokeprueba.repository.PokemonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Pokemon::class], version = 1)
public abstract class AppDatabase : RoomDatabase(){

    abstract fun pokemonDao():PokemonDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "pokemon_database"
                )
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}