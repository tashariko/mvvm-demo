package com.tasha.screens.vehicles.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tasha.data.local.entity.Vehicle
import com.tasha.databinding.ListItemVehicleBinding

class VehicleAdapter : RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>() {

    private var data: List<Vehicle> = ArrayList()
    lateinit var itemClicked: ListVehicleClicked

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

    class VehicleViewHolder(val binding: ListItemVehicleBinding, val itemClicked: ListVehicleClicked) :
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

interface ListVehicleClicked {
    fun itemClicked(item: Vehicle)
}