package com.neal786y.mvparchitecture.main.presenter

import com.neal786y.mvparchitecture.base.BasePresenter
import com.neal786y.mvparchitecture.base.ResponseCallback
import com.neal786y.mvparchitecture.main.interactor.LocationInteractor
import com.neal786y.mvparchitecture.main.pojo.zomato.search_locations.response.LocationResponseDto
import com.neal786y.mvparchitecture.main.view.LocationView
import retrofit2.Retrofit

class LocationPresenter : BasePresenter<LocationView>() {

    fun getLocation(retrofit: Retrofit, query: String) {

        mView?.showLoading()

        val locationInteractor = LocationInteractor(retrofit, query)
        locationInteractor.mLocationResponseCallback = object : ResponseCallback<LocationResponseDto>{
            override fun onSuccess(response: LocationResponseDto) {
                mView?.hideLoading()
                mView?.onLoad(response)
            }

            override fun onError(error: Any) {
                mView?.hideLoading()
                mView?.onError(error)
            }
        }
        locationInteractor.run()
    }
}