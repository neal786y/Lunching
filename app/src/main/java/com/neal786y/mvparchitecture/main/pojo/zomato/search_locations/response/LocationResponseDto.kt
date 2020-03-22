package com.neal786y.mvparchitecture.main.pojo.zomato.search_locations.response

import com.google.gson.annotations.SerializedName

data class LocationResponseDto(

	@field:SerializedName("location_suggestions")
	val locationSuggestions: List<LocationSuggestionsDto?>? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("has_more")
	val hasMore: Int? = null,

	@field:SerializedName("has_total")
	val hasTotal: Int? = null,

	@field:SerializedName("user_has_addresses")
	val userHasAddresses: Boolean? = null
)