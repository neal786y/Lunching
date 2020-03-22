package com.neal786y.mvparchitecture.main.adaper

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dev.neal786y.mvpsample.base.BaseAdapter
import com.neal786y.mvparchitecture.R
import com.neal786y.mvparchitecture.main.listeners.OnRestaurantItemClickListener
import com.neal786y.mvparchitecture.main.pojo.zomato.search_restaurants.response.RestaurantsDto
import com.neal786y.mvparchitecture.main.viewholder.RestaurantViewHolder

class RestaurantAdapter : BaseAdapter<RestaurantsDto, RestaurantViewHolder>() {

    var mOnRestaurantItemClickListener: OnRestaurantItemClickListener? = null

    override fun getViewHolder(viewGroup: ViewGroup, viewType: Int): RestaurantViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val view = inflater.inflate(R.layout.restaurant_row, viewGroup, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.loadData(listOfItems.get(position))
        holder.itemView.setOnClickListener {
            listOfItems.get(holder.adapterPosition).restaurant?.let {
                mOnRestaurantItemClickListener?.onRestaurantItemClick(it)
            }
        }
    }
}