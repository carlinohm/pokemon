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
import com.carlinohm.pokeprueba.databinding.FragmentPokemonsBinding
import com.carlinohm.pokeprueba.db.entity.Pokemon
import com.carlinohm.pokeprueba.ui.adapter.PokemonAdapater
import com.carlinohm.pokeprueba.viewmodel.PokemonViewModel
import com.carlinohm.pokeprueba.viewmodel.PokemonViewModelFactory

class PokemonsFragment : Fragment() {

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
        val binding = DataBindingUtil.inflate<FragmentPokemonsBinding>(
            inflater,
            R.layout.fragment_pokemons,
            container,
            false
        )

        recyclerView = binding.rvPokemons
        mAdapter = PokemonAdapater(mutableListOf<Pokemon>()) { selectPokemon(it) }
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        pokemonViewModel.allWords.observe(this, Observer {
            if (it.isEmpty()) {
                pokemonViewModel.getPokemons(151)
            }
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
        findNavController().navigate(
            PokemonsFragmentDirections.actionPokemonsFragmentToPokemonInfoFragment(
                pokemon.name
            )
        )
    }
}