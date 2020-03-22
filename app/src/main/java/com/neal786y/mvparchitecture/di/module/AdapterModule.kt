package com.neal786y.mvparchitecture.di.module

import com.neal786y.mvparchitecture.main.adaper.RestaurantAdapter
import com.neal786y.mvparchitecture.main.adaper.LocationAdapter
import dagger.Module
import dagger.Provides

@Module
class AdapterModule {

    @Provides
    fun provideRestaurantAdapter() : RestaurantAdapter = RestaurantAdapter()

    @Provides
    fun provideSearchAdapter() : LocationAdapter= LocationAdapter()
}