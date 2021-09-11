package com.example.rickandmorty.locations

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.rickandmorty.R
import com.example.rickandmorty.data.LocationsResults

class LocationItemFragment : Fragment(R.layout.location_item_fragment_layout) {

    private lateinit var locationsDetails: LocationsResults
    private lateinit var locationsDetailsName: TextView
    private lateinit var locationsDetailDimension: TextView
    private lateinit var locationsDetailType: TextView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        locationsDetailsName = view.findViewById(R.id.loc_details_name)
        locationsDetailDimension = view.findViewById(R.id.loc_details_dimension)
        locationsDetailType = view.findViewById(R.id.loc_details_type)
        locationsDetails = arguments?.getSerializable("message") as LocationsResults
        locationsDetailsName.text = locationsDetails.name
        locationsDetailDimension.text = locationsDetails.dimension
        locationsDetailType.text = locationsDetails.type
    }

    companion object {
        fun newInstance(locations: LocationsResults) = LocationItemFragment().apply {
            arguments = Bundle().apply {
                putSerializable("message", locations)
            }
        }

    }

}