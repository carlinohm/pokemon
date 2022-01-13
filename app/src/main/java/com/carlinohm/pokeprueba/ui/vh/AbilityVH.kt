package com.carlinohm.pokeprueba.ui.vh

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.carlinohm.pokeprueba.R
import com.carlinohm.pokeprueba.network.data.AbilityListResponse

class AbilityVH(view: View) : RecyclerView.ViewHolder(view) {
    val tvText = view.findViewById<TextView>(R.id.tvName)
    val ivCheck = view.findViewById<ImageView>(R.id.ivCheck)

    fun bind(ability: AbilityListResponse) {
        tvText.text = ability.ability.name
        if (ability.isHidden) {
            ivCheck.visibility = View.GONE
        } else {
            ivCheck.visibility = View.VISIBLE
        }
    }
}