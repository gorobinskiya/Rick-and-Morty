package com.example.rickandmorty.characters

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.data.CharactersResult

class CharacterItemFragment : Fragment(R.layout.character_item_fragment) {

    private lateinit var characterDetails: CharactersResult
    private lateinit var charDetailsName: TextView
    private lateinit var charDetailsImage: ImageView
    private lateinit var charDetailsOrigin: TextView
    private lateinit var charDetailsGender: TextView
    private lateinit var charDetailsSpecies: TextView
    private lateinit var charDetailStatus: TextView



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        charDetailsName = view.findViewById(R.id.char_details_name)
        charDetailsImage = view.findViewById(R.id.char_details_image)
        charDetailsOrigin = view.findViewById(R.id.char_details_origin)
        charDetailsGender = view.findViewById(R.id.char_details_gender)
        charDetailsSpecies = view.findViewById(R.id.char_details_species)
        charDetailStatus = view.findViewById(R.id.char_details_status)
        characterDetails = arguments?.getSerializable("message") as CharactersResult
        charDetailsName.text = characterDetails.name
        charDetailsOrigin.text = characterDetails.origin.name
        charDetailStatus.text = characterDetails.status
        charDetailsSpecies.text = characterDetails.species
        charDetailsGender.text = characterDetails.gender
        Glide.with(requireContext()).load(characterDetails.image)
            .centerCrop()
            .into(charDetailsImage)
    }

    companion object {
        fun newInstance(character: CharactersResult) = CharacterItemFragment().apply {
            arguments = Bundle().apply {
                putSerializable("message", character)
            }
        }

    }
}