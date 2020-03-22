package com.neal786y.mvparchitecture.main.pojo.zomato.search_restaurants.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class UserDto(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("zomato_handle")
	val zomatoHandle: String? = null,

	@field:SerializedName("foodie_color")
	val foodieColor: String? = null,

	@field:SerializedName("profile_url")
	val profileUrl: String? = null,

	@field:SerializedName("profile_image")
	val profileImage: String? = null,

	@field:SerializedName("profile_deeplink")
	val profileDeeplink: String? = null
)