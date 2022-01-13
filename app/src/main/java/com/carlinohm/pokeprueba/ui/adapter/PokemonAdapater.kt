package com.carlinohm.pokeprueba.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.carlinohm.pokeprueba.R
import com.carlinohm.pokeprueba.db.entity.Pokemon
import com.carlinohm.pokeprueba.ui.vh.PokemonVH

class PokemonAdapater(
    var list: List<Pokemon>,
    val selectPokemon: (Pokemon) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonVH(layoutInflater.inflate(R.layout.list_item_pokemon, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh = holder as PokemonVH
        vh.bind(list[position], selectPokemon)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}