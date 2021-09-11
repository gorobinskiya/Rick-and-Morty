package com.example.rickandmorty.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.data.CharactersResult


class CharactersRecyclerViewAdapter(private val clickLister: CharactersFragment.OnItemNameClickListener) :
    PagingDataAdapter<CharactersResult, CharactersRecyclerViewAdapter.CharacterViewHolder>(
        DiffUtilCallBack()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val characterRowView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.character_item_layout, parent, false)
        return CharacterViewHolder(characterRowView)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.characterNameTextView.text = getItem(position)?.name
        holder.characterGender.text = getItem(position)?.gender
        holder.characterSpecies.text = getItem(position)?.species
        holder.characterStatus.text = getItem(position)?.status
        Glide.with(holder.itemView.context)
            .load(getItem(position)?.image)
            .centerCrop()
            .into(holder.avatarImage)
        getItem(position)?.let { item ->
            holder.itemView.setOnClickListener {
                clickLister.onItemNameClicked(
                    item
                )
            }
        }
    }

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val characterNameTextView: TextView = itemView.findViewById(R.id.char_name)
        val characterStatus: TextView = itemView.findViewById(R.id.char_status)
        val characterGender: TextView = itemView.findViewById(R.id.char_gender)
        val characterSpecies: TextView = itemView.findViewById(R.id.char_species)
        val avatarImage: ImageView = itemView.findViewById(R.id.char_details_image)
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<CharactersResult>() {
        override fun areItemsTheSame(
            oldItem: CharactersResult,
            newItem: CharactersResult
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: CharactersResult,
            newItem: CharactersResult
        ): Boolean {
            return oldItem.name == newItem.name
                    && oldItem.species == newItem.species
        }
    }

}