package com.neal786y.mvparchitecture.main.pojo.zomato.search_restaurants.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class RDto(

	@field:SerializedName("has_menu_status")
	val hasMenuStatus: HasMenuStatusDto? = null,

	@field:SerializedName("res_id")
	val resId: Int? = null
)