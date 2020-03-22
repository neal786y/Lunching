package com.neal786y.mvparchitecture.main.pojo.zomato.search_restaurants.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class AllReviewsDto(

	@field:SerializedName("reviews")
	val reviews: List<ReviewsDto?>? = null
)