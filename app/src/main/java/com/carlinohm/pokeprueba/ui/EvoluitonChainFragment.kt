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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carlinohm.pokeprueba.MyApp
import com.carlinohm.pokeprueba.R
import com.carlinohm.pokeprueba.databinding.FragmentEvoluitonChainBinding
import com.carlinohm.pokeprueba.db.entity.Pokemon
import com.carlinohm.pokeprueba.repository.AbilityRepository
import com.carlinohm.pokeprueba.repository.EvolutionChainRepository
import com.carlinohm.pokeprueba.ui.adapter.PokemonAdapater
import com.carlinohm.pokeprueba.viewmodel.PokemonViewModel
import com.carlinohm.pokeprueba.viewmodel.PokemonViewModelFactory

class EvoluitonChainFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var mAdapter: PokemonAdapater

    private val pokemonViewModel: PokemonViewModel by viewModels {
        PokemonViewModelFactory((requireContext().applicationContext as MyApp).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentEvoluitonChainBinding>(
            inflater,
            R.layout.fragment_evoluiton_chain,
            container,
            false
        )

        recyclerView = binding.rvEvolution
        mAdapter = PokemonAdapater(mutableListOf()) { selectPokemon(it) }
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val args: EvoluitonChainFragmentArgs by navArgs()
        val url = args.url

        pokemonViewModel.getEvolutionChain(url)
        pokemonViewModel.evolutionChain.observe(this, Observer {
            mAdapter.list = it
            mAdapter.notifyDataSetChanged()
        })

        binding.loading.isVisible = false
        pokemonViewModel.isLoading.observe(this, Observer {
            binding.loading.isVisible = it
        })

        return binding.root
    }

    private fun selectPokemon(pokemon: Pokemon) {
        pokemonViewModel.setFavorite(pokemon)
        findNavController().navigate(
            EvoluitonChainFragmentDirections.actionEvoluitonChainFragmentToPokemonsFragment()
        )
    }
}