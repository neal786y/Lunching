package com.neal786y.mvparchitecture.main.listeners

import com.neal786y.mvparchitecture.main.pojo.zomato.search_restaurants.response.RestaurantDto

interface OnRestaurantItemClickListener {
    fun onRestaurantItemClick(restaurantsDto: RestaurantDto)
}