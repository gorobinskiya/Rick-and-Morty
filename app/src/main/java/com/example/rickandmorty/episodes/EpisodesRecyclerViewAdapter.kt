package com.example.rickandmorty.episodes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.data.EpisodesResults


class EpisodesRecyclerViewAdapter (private val clickLister: EpisodesFragment.OnItemNameClickListener) :
    PagingDataAdapter<EpisodesResults, EpisodesRecyclerViewAdapter.EpisodesViewHolder> (
        DiffUtilCallBack()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        val episodesRowView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.episode_item_layout, parent, false)
        return EpisodesViewHolder(episodesRowView)
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        holder.episodeName.text = getItem(position)?.name
        holder.episodeNumber.text = getItem(position)?.episode
        holder.episodeAirDate.text = getItem(position)?.air_date
        getItem(position)?.let { item ->
            holder.itemView.setOnClickListener {
                clickLister.onItemNameClicked(
                    item
                )
            }
        }
    }

    class EpisodesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val episodeName: TextView = itemView.findViewById(R.id.ep_name)
        val episodeNumber: TextView = itemView.findViewById(R.id.ep_episode)
        val episodeAirDate: TextView = itemView.findViewById(R.id.ep_air_date)
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<EpisodesResults>() {
        override fun areItemsTheSame(oldItem: EpisodesResults, newItem: EpisodesResults): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: EpisodesResults,
            newItem: EpisodesResults
        ): Boolean {
            return oldItem.name == newItem.name
                    && oldItem.episode == newItem.episode
        }
    }
}