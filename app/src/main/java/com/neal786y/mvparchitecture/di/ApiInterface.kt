package com.neal786y.mvparchitecture.di

import com.neal786y.mvparchitecture.BuildConfig
import com.neal786y.mvparchitecture.main.pojo.zomato.search_locations.response.LocationResponseDto
import com.neal786y.mvparchitecture.main.pojo.zomato.search_restaurants.response.RestaurantResponseDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    @Headers(
        "Content-Type: application/json",
        "user-key: ${BuildConfig.API_KEY}"
    )
    @GET("locations")
    fun getLocations(@Query("query") query: String, @Query("count") count: Int = 10): Call<LocationResponseDto>

    @Headers(
        "Content-Type: application/json",
        "user-key: ${BuildConfig.API_KEY}"
    )
    @GET("search")
    fun getRestaurantsByEntityId(@Query("entity_id") entityId: Int, @Query("entity_type") entityType: String): Call<RestaurantResponseDto>

    @Headers(
        "Content-Type: application/json",
        "user-key: ${BuildConfig.API_KEY}"
    )
    @GET("search")
    fun getRestaurantsByLatLon(@Query("lat") lat: Double, @Query("lon") lon: Double): Call<RestaurantResponseDto>
}