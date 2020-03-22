package com.neal786y.mvparchitecture.main.pojo.zomato.search_restaurants.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class HasMenuStatusDto(

	@field:SerializedName("delivery")
	val delivery: Int? = null,

	@field:SerializedName("takeaway")
	val takeaway: Int? = null
)