package com.example.rickandmorty.episodes

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.rickandmorty.R
import com.example.rickandmorty.data.EpisodesResults

class EpisodeItemFragment : Fragment(R.layout.episode_item_fragment_layout) {

    private lateinit var episodeDetails: EpisodesResults
    private lateinit var episodeDetailsName: TextView
    private lateinit var episodeDetailsEpisode: TextView
    private lateinit var episodeDetailsAirDate: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        episodeDetailsName = view.findViewById(R.id.ep_details_name)
        episodeDetailsEpisode = view.findViewById(R.id.ep_details_episode)
        episodeDetailsAirDate = view.findViewById(R.id.ep_details_air_date)
        episodeDetails = arguments?.getSerializable("message") as EpisodesResults
        episodeDetailsName.text = episodeDetails.name
        episodeDetailsEpisode.text = episodeDetails.episode
        episodeDetailsAirDate.text = episodeDetails.air_date

    }

    companion object {
        fun newInstance(episode: EpisodesResults) = EpisodeItemFragment().apply {
            arguments = Bundle().apply {
                putSerializable("message", episode)
            }
        }

    }

}