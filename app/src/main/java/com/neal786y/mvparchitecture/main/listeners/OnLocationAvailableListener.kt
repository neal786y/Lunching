package com.neal786y.mvparchitecture.main.listeners

import android.location.Location

interface OnLocationAvailableListener {
    fun onLocationAvailable(location: Location)
    fun onPermisionRequired()
    fun checkPermission() : Boolean
}