package com.neal786y.mvparchitecture.main.interactor

import com.neal786y.mvparchitecture.base.Interactor
import com.neal786y.mvparchitecture.base.ResponseCallback
import com.neal786y.mvparchitecture.di.ApiInterface
import com.neal786y.mvparchitecture.main.pojo.zomato.search_restaurants.response.RestaurantResponseDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RestauantsByLatLonInteractor (var mRetrofit: Retrofit, var lat: Double, var lon: Double) :
    Interactor<RestaurantResponseDto> {

    var mRestaurantResponseCallback: ResponseCallback<RestaurantResponseDto>? = null

    override fun run() {

        val apiClient = mRetrofit.create<ApiInterface>(ApiInterface::class.java)

        val call = apiClient.getRestaurantsByLatLon(lat, lon)

        call.enqueue(object : Callback<RestaurantResponseDto> {
            override fun onFailure(call: Call<RestaurantResponseDto>, t: Throwable) {
                onError(t)
            }

            override fun onResponse(call: Call<RestaurantResponseDto>, response: Response<RestaurantResponseDto>) {
                onSuccess(response.body() as RestaurantResponseDto)
            }

        })
    }

    override fun onSuccess(response: RestaurantResponseDto) {
        mRestaurantResponseCallback?.onSuccess(response)
    }

    override fun onError(error: Any) {
        mRestaurantResponseCallback?.onError(error)
    }
}