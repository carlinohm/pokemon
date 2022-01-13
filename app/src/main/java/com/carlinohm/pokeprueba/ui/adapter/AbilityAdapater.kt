package com.carlinohm.pokeprueba.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.carlinohm.pokeprueba.R
import com.carlinohm.pokeprueba.network.data.AbilityListResponse
import com.carlinohm.pokeprueba.ui.vh.AbilityVH

class AbilityAdapater(
    var list: List<AbilityListResponse>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AbilityVH(layoutInflater.inflate(R.layout.list_item_ability, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh = holder as AbilityVH
        vh.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}