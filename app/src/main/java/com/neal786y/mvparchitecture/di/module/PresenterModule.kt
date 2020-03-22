package com.neal786y.mvparchitecture.di.module

import com.neal786y.mvparchitecture.main.presenter.DetailWebPresenter
import com.neal786y.mvparchitecture.main.presenter.LocationPresenter
import com.neal786y.mvparchitecture.main.presenter.RestaurantPresenter
import com.neal786y.mvparchitecture.main.presenter.SplashPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {

    @Provides
    @Singleton
    fun provideDetailWebPresenter(): DetailWebPresenter = DetailWebPresenter()

    @Provides
    @Singleton
    fun provideSplashPresenter(): SplashPresenter =
        SplashPresenter()

    @Provides
    @Singleton
    fun provideRestaurantPresenter(): RestaurantPresenter = RestaurantPresenter()

    @Provides
    @Singleton
    fun provideLocationPresenter(): LocationPresenter = LocationPresenter()
}