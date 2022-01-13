package com.carlinohm.pokeprueba.ui.vh

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.carlinohm.pokeprueba.R
import com.carlinohm.pokeprueba.db.entity.Pokemon

class PokemonVH(view: View) : RecyclerView.ViewHolder(view) {
    val tvText = view.findViewById<TextView>(R.id.tvName)

    fun bind(pokemon: Pokemon, selectPokemon: (Pokemon) -> Unit) {
        var fav = ""
        if (pokemon.fav == 1) {
            fav = "Favorito - "
        }
        if (pokemon.fav == 2) {
            fav = "Error - "
        }
        tvText.text = "$fav${pokemon.name}"
        itemView.setOnClickListener { selectPokemon(pokemon) }
    }
}