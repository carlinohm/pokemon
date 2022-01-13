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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carlinohm.pokeprueba.MyApp
import com.carlinohm.pokeprueba.R
import com.carlinohm.pokeprueba.databinding.FragmentAbilitiesBinding
import com.carlinohm.pokeprueba.db.entity.Pokemon
import com.carlinohm.pokeprueba.repository.AbilityRepository
import com.carlinohm.pokeprueba.ui.adapter.AbilityAdapater
import com.carlinohm.pokeprueba.ui.adapter.PokemonAdapater
import com.carlinohm.pokeprueba.viewmodel.AbilityViewModel
import com.carlinohm.pokeprueba.viewmodel.AbilityViewModelFactory

class AbilitiesFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var mAdapter: AbilityAdapater

    private val abilityViewModel: AbilityViewModel by viewModels {
        AbilityViewModelFactory(AbilityRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentAbilitiesBinding>(
            inflater,
            R.layout.fragment_abilities,
            container,
            false
        )

        recyclerView = binding.rvAbilities
        mAdapter = AbilityAdapater(mutableListOf())
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val args: PokemonInfoFragmentArgs by navArgs()
        val name = args.name

        abilityViewModel.getAbilities(name)
        abilityViewModel.abilityList.observe(this, Observer {
            mAdapter.list = it
            mAdapter.notifyDataSetChanged()
        })

        binding.loading.isVisible = false
        abilityViewModel.isLoading.observe(this, Observer {
            binding.loading.isVisible = it
        })

        return binding.root
    }
}