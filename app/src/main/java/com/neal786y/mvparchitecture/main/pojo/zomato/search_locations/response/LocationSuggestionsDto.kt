package com.neal786y.mvparchitecture.main.pojo.zomato.search_locations.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LocationSuggestionsDto(

	@field:SerializedName("entity_type")
	val entityType: String? = null,

	@field:SerializedName("entity_id")
	val entityId: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("latitude")
	val latitude: Any? = null,

	@field:SerializedName("longitude")
	val longitude: Any? = null,

	@field:SerializedName("city_id")
	val cityId: Int? = null,

	@field:SerializedName("city_name")
	val cityName: String? = null,

	@field:SerializedName("country_id")
	val countryId: Int? = null,

	@field:SerializedName("country_name")
	val countryName: String? = null
)