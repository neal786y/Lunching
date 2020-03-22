package com.neal786y.mvparchitecture.main.adaper

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dev.neal786y.mvpsample.base.BaseAdapter
import com.neal786y.mvparchitecture.R
import com.neal786y.mvparchitecture.main.listeners.OnLocationItemClickListener
import com.neal786y.mvparchitecture.main.pojo.zomato.search_locations.response.LocationSuggestionsDto
import com.neal786y.mvparchitecture.main.viewholder.LocationViewHolder

class LocationAdapter  : BaseAdapter<LocationSuggestionsDto, LocationViewHolder>() {

    var mOnLocationItemClickListener: OnLocationItemClickListener? = null

    override fun getViewHolder(viewGroup: ViewGroup, viewType: Int): LocationViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val view = inflater.inflate(R.layout.location_row, viewGroup, false)
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.loadData(listOfItems.get(position))
        holder.itemView.setOnClickListener {
            mOnLocationItemClickListener?.onLocationItemClick(listOfItems.get(holder.adapterPosition))
        }
    }
}