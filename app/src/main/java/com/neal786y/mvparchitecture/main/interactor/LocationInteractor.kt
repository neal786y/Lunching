package com.neal786y.mvparchitecture.main.interactor

import com.neal786y.mvparchitecture.base.Interactor
import com.neal786y.mvparchitecture.base.ResponseCallback
import com.neal786y.mvparchitecture.di.ApiInterface
import com.neal786y.mvparchitecture.main.pojo.zomato.search_locations.response.LocationResponseDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class LocationInteractor (var mRetrofit: Retrofit, var query: String) : Interactor<LocationResponseDto> {

    var mLocationResponseCallback: ResponseCallback<LocationResponseDto>? = null

    override fun run() {

        val apiClient = mRetrofit.create<ApiInterface>(ApiInterface::class.java)

        val call = apiClient.getLocations(query)

        call.enqueue(object : Callback<LocationResponseDto> {
            override fun onFailure(call: Call<LocationResponseDto>, t: Throwable) {
                onError(t)
            }

            override fun onResponse(call: Call<LocationResponseDto>, response: Response<LocationResponseDto>) {
                onSuccess(response.body() as LocationResponseDto)
            }
        })
    }

    override fun onSuccess(response: LocationResponseDto) {
        mLocationResponseCallback?.onSuccess(response)
    }

    override fun onError(error: Any) {
        mLocationResponseCallback?.onError(error)
    }
}