package com.neal786y.mvparchitecture.main.pojo.zomato.search_restaurants.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class RestaurantResponseDto(

	@field:SerializedName("results_found")
	val resultsFound: Int? = null,

	@field:SerializedName("results_start")
	val resultsStart: Int? = null,

	@field:SerializedName("results_shown")
	val resultsShown: Int? = null,

	@field:SerializedName("restaurants")
	val restaurants: List<RestaurantsDto?>? = null
)