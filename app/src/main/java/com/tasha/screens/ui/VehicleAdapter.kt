package com.tasha.screens.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.tasha.data.local.entity.Vehicle
import com.tasha.databinding.ListItemVehicleBinding
import java.util.*

class VehicleAdapter : RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>() {

    private var data: List<Vehicle> = ArrayList()
    lateinit var itemClicked: ListItemClicked

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) = VehicleViewHolder(
        ListItemVehicleBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        ),
        itemClicked
    )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) =
        holder.bind(data[position])

    fun swapData(data: List<Vehicle>) {
        this.data = data
        notifyDataSetChanged()
    }

    class VehicleViewHolder(val binding: ListItemVehicleBinding, val itemClicked: ListItemClicked) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Vehicle) = with(binding) {
            titleView.text = "${item.name}"
            manufacturerView.text = "Constructed by: ${item.manufacturer}"

            parentView.setOnClickListener {
                itemClicked.itemClicked(item)
            }
        }
    }
}

interface ListItemClicked {
    fun itemClicked(item: Vehicle)
}