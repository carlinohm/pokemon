package com.carlinohm.pokeprueba

import android.app.Application
import com.carlinohm.pokeprueba.db.AppDatabase
import com.carlinohm.pokeprueba.repository.PokemonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyApp: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { PokemonRepository(database.pokemonDao()) }
}