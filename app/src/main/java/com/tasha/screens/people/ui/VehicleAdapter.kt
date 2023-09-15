package com.tasha.screens.people.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tasha.data.local.entity.People
import com.tasha.databinding.ListItemPeopleBinding

class PeopleAdapter : RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>() {

    private var data: List<People> = ArrayList()
    lateinit var itemClicked: ListPeopleClicked

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) = PeopleViewHolder(
        ListItemPeopleBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        ),
        itemClicked
    )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) =
        holder.bind(data[position])

    fun swapData(data: List<People>) {
        this.data = data
        notifyDataSetChanged()
    }

    class PeopleViewHolder(val binding: ListItemPeopleBinding, val itemClicked: ListPeopleClicked) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: People) = with(binding) {
            titleView.text = "${item.name}"
            manufacturerView.text = "Constructed by: ${item.gender}"

            parentView.setOnClickListener {
                itemClicked.itemClicked(item)
            }
        }
    }
}

interface ListPeopleClicked {
    fun itemClicked(item: People)
}