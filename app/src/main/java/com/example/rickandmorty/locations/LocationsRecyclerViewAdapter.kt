package com.example.rickandmorty.locations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.data.LocationsResults


class LocationsRecyclerViewAdapter(private val clickListener: LocationFragment.OnItemNameClickListener) :
    PagingDataAdapter<LocationsResults, LocationsRecyclerViewAdapter.LocationsViewHolder>(
        DiffUtilCallBack()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        val locationRowView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.location_item_layout, parent, false)
        return LocationsViewHolder(locationRowView)
    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        holder.locationName.text = getItem(position)?.name
        holder.locationDimension.text = getItem(position)?.dimension
        holder.locationType.text = getItem(position)?.type

        getItem(position)?.let { item ->
            holder.itemView.setOnClickListener {
                clickListener.onItemNameClicked(
                    item
                )
            }
        }

    }


    class LocationsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val locationName: TextView = itemView.findViewById(R.id.loc_name)
        val locationDimension: TextView = itemView.findViewById(R.id.loc_dimension)
        val locationType: TextView = itemView.findViewById(R.id.loc_type)
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<LocationsResults>() {
        override fun areItemsTheSame(
            oldItem: LocationsResults,
            newItem: LocationsResults
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: LocationsResults,
            newItem: LocationsResults
        ): Boolean {
            return oldItem.name == newItem.name
                    && oldItem.dimension == newItem.dimension
        }

    }

}