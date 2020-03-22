package com.neal786y.mvparchitecture.main.listeners

import com.neal786y.mvparchitecture.main.pojo.zomato.search_locations.response.LocationSuggestionsDto

interface OnLocationItemClickListener {
    fun onLocationItemClick(locationSuggestionDto: LocationSuggestionsDto)
}