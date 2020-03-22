package com.neal786y.mvparchitecture.main.presenter

import com.neal786y.mvparchitecture.base.BasePresenter
import com.neal786y.mvparchitecture.base.ResponseCallback
import com.neal786y.mvparchitecture.main.interactor.RestauantsByLatLonInteractor
import com.neal786y.mvparchitecture.main.interactor.RestaurantsByEntityIdInteractor
import com.neal786y.mvparchitecture.main.pojo.zomato.search_restaurants.response.RestaurantResponseDto
import com.neal786y.mvparchitecture.main.view.RestaurantView
import retrofit2.Retrofit

class RestaurantPresenter : BasePresenter<RestaurantView>() {

    fun getRestaurantsByEntityId(retrofit: Retrofit, entityId: Int, entityType: String){

        mView?.showLoading()

        val restarantInteractor = RestaurantsByEntityIdInteractor(retrofit, entityId, entityType)
        restarantInteractor.mRestaurantResponseCallback = object : ResponseCallback<RestaurantResponseDto>{
            override fun onSuccess(response: RestaurantResponseDto) {
                mView?.hideLoading()
                mView?.onLoad(response)
            }

            override fun onError(error: Any) {
                mView?.hideLoading()
                mView?.onError(error)
            }
        }
        restarantInteractor.run()
    }

    fun getRestaurantsByLatLon(retrofit: Retrofit, lat: Double, lon: Double){

        mView?.showLoading()

        val restarantInteractor = RestauantsByLatLonInteractor(retrofit, lat, lon)
        restarantInteractor.mRestaurantResponseCallback = object : ResponseCallback<RestaurantResponseDto>{
            override fun onSuccess(response: RestaurantResponseDto) {
                mView?.hideLoading()
                mView?.onLoad(response)
            }

            override fun onError(error: Any) {
                mView?.hideLoading()
                mView?.onError(error)
            }
        }
        restarantInteractor.run()
    }
}