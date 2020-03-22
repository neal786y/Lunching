package com.neal786y.mvparchitecture.main.pojo.zomato.search_restaurants.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class UserRatingDto(

	@field:SerializedName("aggregate_rating")
	val aggregateRating: Double? = null,

	@field:SerializedName("rating_text")
	val ratingText: String? = null,

	@field:SerializedName("rating_color")
	val ratingColor: String? = null,

	@field:SerializedName("rating_obj")
	val ratingObj: RatingObjDto? = null,

	@field:SerializedName("votes")
	val votes: Int? = null,

	@field:SerializedName("custom_rating_text")
	val customRatingText: String? = null,

	@field:SerializedName("custom_rating_text_background")
	val customRatingTextBackground: String? = null,

	@field:SerializedName("rating_tool_tip")
	val ratingToolTip: String? = null
)