package com.neal786y.mvparchitecture.main.pojo.zomato.search_restaurants.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class LocationDto(

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("locality")
	val locality: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("city_id")
	val cityId: Int? = null,

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null,

	@field:SerializedName("zipcode")
	val zipcode: String? = null,

	@field:SerializedName("country_id")
	val countryId: Int? = null,

	@field:SerializedName("locality_verbose")
	val localityVerbose: String? = null
)