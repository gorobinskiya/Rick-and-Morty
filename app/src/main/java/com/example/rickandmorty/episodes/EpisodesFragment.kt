package com.example.rickandmorty.episodes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.data.EpisodesResults
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class EpisodesFragment : Fragment(R.layout.episodes_fragment) {

    private val viewModel by viewModel<EpisodesViewModel>()

    private val episodesAdapter by lazy { EpisodesRecyclerViewAdapter(object : OnItemNameClickListener {
        override fun onItemNameClicked(episode: EpisodesResults) {
            val episodeItemFragment : Fragment
            episodeItemFragment = EpisodeItemFragment.newInstance(episode)
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, episodeItemFragment)
            transaction.addToBackStack("FRAGMENT_CHARACTER_TAG")
            transaction.commit()


        }
    }) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val episodesListView = view.findViewById<RecyclerView>(R.id.episodeRecycleView)
        episodesListView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        episodesListView.adapter = episodesAdapter
        initViewModel()
    }

    private fun initViewModel() {
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest {
                episodesAdapter.submitData(it)
            }
        }
    }
    interface OnItemNameClickListener {
        fun onItemNameClicked(episode: EpisodesResults)
    }


}

