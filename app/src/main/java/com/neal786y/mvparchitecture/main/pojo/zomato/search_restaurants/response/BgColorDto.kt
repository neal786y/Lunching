package com.neal786y.mvparchitecture.main.pojo.zomato.search_restaurants.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class BgColorDto(

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("tint")
	val tint: String? = null
)