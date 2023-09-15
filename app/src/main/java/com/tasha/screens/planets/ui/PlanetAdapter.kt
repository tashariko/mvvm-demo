package com.tasha.screens.planets.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tasha.data.local.entity.Planet
import com.tasha.databinding.ListItemPlanetBinding

class PlanetAdapter : RecyclerView.Adapter<PlanetAdapter.PlanetViewHolder>() {

    private var data: List<Planet> = ArrayList()
    lateinit var itemClicked: ListPlanetClicked

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) = PlanetViewHolder(
        ListItemPlanetBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        ),
        itemClicked
    )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) =
        holder.bind(data[position])

    fun swapData(data: List<Planet>) {
        this.data = data
        notifyDataSetChanged()
    }

    class PlanetViewHolder(val binding: ListItemPlanetBinding, val itemClicked: ListPlanetClicked) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Planet) = with(binding) {
            titleView.text = "${item.name}"
            manufacturerView.text = "Constructed by: ${item.terrain}"

            parentView.setOnClickListener {
                itemClicked.itemClicked(item)
            }
        }
    }
}

interface ListPlanetClicked {
    fun itemClicked(item: Planet)
}