package com.neal786y.mvparchitecture.main.pojo.zomato.search_restaurants.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class PhotoDto(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("thumb_url")
	val thumbUrl: String? = null,

	@field:SerializedName("user")
	val user: UserDto? = null,

	@field:SerializedName("res_id")
	val resId: Int? = null,

	@field:SerializedName("caption")
	val caption: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: Int? = null,

	@field:SerializedName("friendly_time")
	val friendlyTime: String? = null,

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("height")
	val height: Int? = null
)