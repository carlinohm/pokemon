package com.carlinohm.pokeprueba.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.carlinohm.pokeprueba.network.data.AbilityListResponse
import com.carlinohm.pokeprueba.repository.AbilityRepository
import kotlinx.coroutines.launch

class AbilityViewModel(private val abilityRepository: AbilityRepository) :
    ViewModel() {
    val abilityList = MutableLiveData<List<AbilityListResponse>>()

    val isLoading = MutableLiveData<Boolean>()

    fun getAbilities(name: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = abilityRepository.getPokemonAbilities(name)
            abilityList.postValue(result)
            isLoading.postValue(false)
        }
    }
}

class AbilityViewModelFactory(private val abilityRepository: AbilityRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AbilityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AbilityViewModel(abilityRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
