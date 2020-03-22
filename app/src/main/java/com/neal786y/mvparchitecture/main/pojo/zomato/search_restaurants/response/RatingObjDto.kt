package com.neal786y.mvparchitecture.main.pojo.zomato.search_restaurants.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class RatingObjDto(

	@field:SerializedName("title")
	val title: TitleDto? = null,

	@field:SerializedName("bg_color")
	val bgColor: BgColorDto? = null
)