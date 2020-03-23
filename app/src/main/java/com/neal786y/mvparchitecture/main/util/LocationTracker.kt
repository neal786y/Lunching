package com.neal786y.mvparchitecture.main.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.neal786y.mvparchitecture.R
import com.neal786y.mvparchitecture.main.listeners.OnLocationAvailableListener

class LocationTracker(var activity: Activity) {

    var mFusedLocationClient: FusedLocationProviderClient

    lateinit var onLocationAvailableListener: OnLocationAvailableListener

    init {

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)

    }

    @SuppressLint("MissingPermission")
    fun getLastLocation() {
        if (onLocationAvailableListener.checkPermission()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.lastLocation.addOnCompleteListener(
                    object : OnCompleteListener<Location?> {
                        override fun onComplete(task: Task<Location?>) {
                            val location: Location? = task.getResult()
                            if (location == null) {
                                requestNewLocationData()
                            } else {
                                onLocationAvailableListener.onLocationAvailable(location)
                            }
                        }
                    }
                )
            } else {
                showSettingsAlert()
            }
        } else {
            onLocationAvailableListener.onPermisionRequired()
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation = locationResult.lastLocation
            onLocationAvailableListener.onLocationAvailable(mLastLocation)

        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager =
            activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    fun showSettingsAlert() {
        val alertDialog =
            AlertDialog.Builder(activity)
        alertDialog.setTitle(activity.getString(R.string.gps_is_not_enabled_text))
        alertDialog.setMessage(activity.getString(R.string.turn_on_gps_msg))
        alertDialog.setPositiveButton(activity.getString(R.string.yes_text)) { dialog, which ->
            val intent =
                Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            activity.startActivity(intent)
        }
        alertDialog.setNegativeButton(activity.getString(R.string.no_text)) { dialog, which -> dialog.cancel() }
        alertDialog.show()
    }
}