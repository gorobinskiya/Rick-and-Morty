package com.example.rickandmorty.characters

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.data.CharactersResult
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class CharactersFragment : Fragment(R.layout.fragment_characters), KoinComponent {

    companion object {
        const val GRID_SPAN_COUNT = 2
    }

    private val viewModel by viewModel<CharactersViewModel>()

    private val characterAdapter by lazy {
        CharactersRecyclerViewAdapter(object : OnItemNameClickListener {
            override fun onItemNameClicked(character: CharactersResult) {
                Log.d("CheckClickEVENT", "$character")
                val characterItemFragment: Fragment
                characterItemFragment = CharacterItemFragment.newInstance(character)
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container, characterItemFragment)
                transaction.addToBackStack("FRAGMENT_CHARACTER_TAG")
                transaction.commit()


            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val characterListView = view.findViewById<RecyclerView>(R.id.characterRecycleView)
        characterListView.layoutManager =
            GridLayoutManager(context, GRID_SPAN_COUNT, LinearLayoutManager.VERTICAL, false)
        characterListView.adapter = characterAdapter

        initViewModel()
    }

    private fun initViewModel() {
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest {
                characterAdapter.submitData(it)
            }
        }
    }

    interface OnItemNameClickListener {
        fun onItemNameClicked(character: CharactersResult)
    }
}
