package com.neal786y.mvparchitecture.main.viewholder

import android.graphics.Color
import android.view.View
import com.dev.neal786y.mvpsample.base.BaseViewHolder
import com.neal786y.mvparchitecture.base.loadImageUrl
import com.neal786y.mvparchitecture.main.pojo.zomato.search_restaurants.response.RestaurantsDto
import kotlinx.android.synthetic.main.restaurant_row.view.*

class RestaurantViewHolder(itemView: View) : BaseViewHolder<RestaurantsDto>(itemView) {

    override fun loadData(data: RestaurantsDto?) {

        data?.let {
            with(itemView){
                imageViewRestaurant.loadImageUrl(it.restaurant?.thumb)
                textViewRestaurantName.text = it.restaurant?.name
                textViewCuisines.text = it.restaurant?.cuisines
                textViewArea.text = it.restaurant?.location?.localityVerbose
                textViewRatings.text = it.restaurant?.userRating?.aggregateRating.toString()
                textViewRatings.setBackgroundColor(Color.parseColor("#${it.restaurant?.userRating?.ratingColor}"))
            }
        }
    }
}