package com.carlinohm.pokeprueba.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.*
import com.carlinohm.pokeprueba.db.entity.Pokemon
import com.carlinohm.pokeprueba.network.data.EvolutionChainResponse
import com.carlinohm.pokeprueba.network.data.PokemonSpeciesResponse
import com.carlinohm.pokeprueba.repository.PokemonRepository
import kotlinx.coroutines.launch

class PokemonViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {

    val allWords: LiveData<List<Pokemon>> = pokemonRepository.allPokemons.asLiveData()

    val evolutionChain = MutableLiveData<List<Pokemon>>()

    val isLoading = MutableLiveData<Boolean>()

    fun update(pokemon: Pokemon) = viewModelScope.launch {
        pokemonRepository.update(pokemon)
    }


    fun getPokemons(limit: Int) = viewModelScope.launch {
        isLoading.postValue(true)
        pokemonRepository.getPokemons(limit)
        isLoading.postValue(false)
    }

    fun getEvolutionChain(url: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = pokemonRepository.getEvolutionChain(url)
            val list = mutableListOf<Pokemon>()
            result.chain.preOrder(list)
            evolutionChain.postValue(list)
            isLoading.postValue(false)
        }
    }

    fun setFavorite(pokemon: Pokemon) {
        viewModelScope.launch {
            val result = pokemonRepository.setFavorite()
            if (result) {
                pokemon.fav = 1
            } else {
                pokemon.fav = 2
            }
            update(pokemon)
            object : CountDownTimer(5000, 1000) {
                override fun onTick(p0: Long) {
                    return
                }

                override fun onFinish() {
                    pokemon.fav = 0
                    update(pokemon)
                }

            }.start()
        }
    }

}

class PokemonViewModelFactory(private val pokemonRepository: PokemonRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PokemonViewModel(pokemonRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
