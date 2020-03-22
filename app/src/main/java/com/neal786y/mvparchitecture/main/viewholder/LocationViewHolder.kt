package com.neal786y.mvparchitecture.main.viewholder

import android.view.View
import com.dev.neal786y.mvpsample.base.BaseViewHolder
import com.neal786y.mvparchitecture.main.pojo.zomato.search_locations.response.LocationSuggestionsDto
import kotlinx.android.synthetic.main.location_row.view.*

class LocationViewHolder(itemView: View) : BaseViewHolder<LocationSuggestionsDto>(itemView) {
    override fun loadData(data: LocationSuggestionsDto?) {
        data?.let {
            with(itemView){
                textViewTitle.text = it.title
                textViewCityName.text = it.cityName
            }
        }
    }
}