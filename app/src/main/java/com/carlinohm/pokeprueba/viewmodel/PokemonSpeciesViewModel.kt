package com.carlinohm.pokeprueba.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.carlinohm.pokeprueba.network.data.PokemonSpeciesResponse
import com.carlinohm.pokeprueba.repository.PokemonSpeciesRepository
import kotlinx.coroutines.launch

class PokemonSpeciesViewModel(private val pokemonSpeciesRepository: PokemonSpeciesRepository) :
    ViewModel() {
    val pokemonSpecies = MutableLiveData<PokemonSpeciesResponse>()

    val isLoading = MutableLiveData<Boolean>()

    fun getSpeciesInfo(name: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = pokemonSpeciesRepository.getSpeciesInfo(name)
            pokemonSpecies.postValue(result)
            isLoading.postValue(false)
        }
    }
}

class PokemonSpeciesViewModelFactory(private val pokemonSpeciesRepository: PokemonSpeciesRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonSpeciesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PokemonSpeciesViewModel(pokemonSpeciesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
