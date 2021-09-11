package com.example.rickandmorty.locations

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.data.LocationsResults
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class LocationFragment : Fragment(R.layout.fragment_location), KoinComponent {

    companion object {
        const val GRID_SPAN_COUNT = 2
    }

    private val viewModel by viewModel<LocationsViewModel>()

    private val locationsAdapter by lazy {
        LocationsRecyclerViewAdapter(object : OnItemNameClickListener {
            override fun onItemNameClicked(location: LocationsResults) {
                val locationItemFragment: Fragment
                locationItemFragment = LocationItemFragment.newInstance(location)
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container, locationItemFragment)
                transaction.addToBackStack("FRAGMENTS_LOCATION_TAG")
                transaction.commit()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val locationsListView = view.findViewById<RecyclerView>(R.id.locationRecycleView)
        locationsListView.layoutManager =
            GridLayoutManager(context, GRID_SPAN_COUNT, LinearLayoutManager.VERTICAL, false)
        locationsListView.adapter = locationsAdapter
        initViewModel()
    }

    private fun initViewModel() {
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest {
                locationsAdapter.submitData(it)
            }
        }
    }

    interface OnItemNameClickListener {
        fun onItemNameClicked(location: LocationsResults)
    }
}

