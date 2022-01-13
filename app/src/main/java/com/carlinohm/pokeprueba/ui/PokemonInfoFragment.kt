package com.carlinohm.pokeprueba.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.carlinohm.pokeprueba.MyApp
import com.carlinohm.pokeprueba.R
import com.carlinohm.pokeprueba.databinding.FragmentPokemonInfoBinding
import com.carlinohm.pokeprueba.repository.PokemonSpeciesRepository
import com.carlinohm.pokeprueba.viewmodel.PokemonSpeciesViewModel
import com.carlinohm.pokeprueba.viewmodel.PokemonSpeciesViewModelFactory
import com.carlinohm.pokeprueba.viewmodel.PokemonViewModel
import com.carlinohm.pokeprueba.viewmodel.PokemonViewModelFactory

class PokemonInfoFragment : Fragment() {

    private val pokemonSpeciesViewModel: PokemonSpeciesViewModel by viewModels {
        PokemonSpeciesViewModelFactory(PokemonSpeciesRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPokemonInfoBinding>(
            inflater,
            R.layout.fragment_pokemon_info,
            container,
            false
        )

        val args: PokemonInfoFragmentArgs by navArgs()
        val name = args.name

        pokemonSpeciesViewModel.getSpeciesInfo(name)
        binding.tvSpeciesName.text = name
        pokemonSpeciesViewModel.pokemonSpecies.observe(this, Observer {
            binding.tvBaseHappinessValue.text = it.baseHappiness.toString()
            binding.tvCaptureRatioValue.text = it.captureRate.toString()
            binding.tvEggGroupsValue.text = it.eggGroups.joinToString(", ")

            binding.btnEvolutionChain.setOnClickListener { view ->
                findNavController().navigate(
                    PokemonInfoFragmentDirections.actionPokemonInfoFragmentToEvoluitonChainFragment(
                        it.evolutionChainUrl.url
                    )
                )
            }
        })

        binding.loading.isVisible = false
        pokemonSpeciesViewModel.isLoading.observe(this, Observer {
            binding.loading.isVisible = it
        })

        binding.btnSkills.setOnClickListener {
            findNavController().navigate(
                PokemonInfoFragmentDirections.actionPokemonInfoFragmentToAbilitiesFragment(
                    name
                )
            )
        }
        return binding.root
    }
}