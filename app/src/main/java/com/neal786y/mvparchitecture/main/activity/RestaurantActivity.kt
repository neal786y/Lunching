package com.neal786y.mvparchitecture.main.activity

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.neal786y.mvparchitecture.R
import com.neal786y.mvparchitecture.base.*
import com.neal786y.mvparchitecture.base.Constants.Companion.ENTITY_ID
import com.neal786y.mvparchitecture.base.Constants.Companion.ENTITY_TYPE
import com.neal786y.mvparchitecture.base.Constants.Companion.LOCATION_REQUEST_CODE
import com.neal786y.mvparchitecture.base.Constants.Companion.PERMISSION_REQUEST_CODE
import com.neal786y.mvparchitecture.main.adaper.RestaurantAdapter
import com.neal786y.mvparchitecture.main.listeners.OnLocationAvailableListener
import com.neal786y.mvparchitecture.main.listeners.OnRestaurantItemClickListener
import com.neal786y.mvparchitecture.main.pojo.zomato.search_restaurants.response.RestaurantDto
import com.neal786y.mvparchitecture.main.pojo.zomato.search_restaurants.response.RestaurantResponseDto
import com.neal786y.mvparchitecture.main.pojo.zomato.search_restaurants.response.RestaurantsDto
import com.neal786y.mvparchitecture.main.presenter.RestaurantPresenter
import com.neal786y.mvparchitecture.main.util.LocationTracker
import com.neal786y.mvparchitecture.main.view.RestaurantView
import kotlinx.android.synthetic.main.activity_restaurant.*
import retrofit2.Retrofit
import javax.inject.Inject


class RestaurantActivity : BaseActivity<RestaurantView, RestaurantPresenter>(), RestaurantView {

    val TAG = this::class.java.simpleName

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var restaurantPresenter: RestaurantPresenter

    @Inject
    lateinit var restaurantAdapter: RestaurantAdapter

    lateinit var locationTracker: LocationTracker

    override fun getLayout(): Int = R.layout.activity_restaurant

    override fun initPresenter(): RestaurantPresenter = restaurantPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)

        linearLayoutSearch.setOnClickListener {
            val intent = Intent(this@RestaurantActivity, LocationActivity::class.java)
            startActivityForResult(intent, LOCATION_REQUEST_CODE)
        }

        recyclerViewRestaurants.layoutManager = LinearLayoutManager(this)
        recyclerViewRestaurants.adapter = restaurantAdapter
        recyclerViewRestaurants.setEmptyView(textViewEmptyMessage)

        restaurantAdapter.mOnRestaurantItemClickListener = object : OnRestaurantItemClickListener {
            override fun onRestaurantItemClick(restaurantsDto: RestaurantDto) {
                val intent = Intent(this@RestaurantActivity, DetailWebActivity::class.java)
                intent.putExtra(getString(R.string.detailUrlKey), restaurantsDto.url)
                startActivity(intent)
            }
        }

        locationTracker = LocationTracker(this)

        locationTracker.onLocationAvailableListener = object : OnLocationAvailableListener {
            override fun onLocationAvailable(location: Location) {
                val longitude: Double = location.getLongitude()
                val latitude: Double = location.getLatitude()
                presenter?.getRestaurantsByLatLon(retrofit, latitude, longitude);
            }

            override fun onPermisionRequired() {
                requestPermission(this@RestaurantActivity, Constants.PERMISSION_REQUEST_CODE)
            }

            override fun checkPermission(): Boolean {
                return isPermissionGranted(this@RestaurantActivity)
            }

        }

        if (!isPermissionGranted(this)) requestPermission(this, PERMISSION_REQUEST_CODE)
        else locationTracker.getLastLocation()
    }

    private fun fetchRestaurantsByEntityId(entityId: Int, entityType: String) {
        presenter?.getRestaurantsByEntityId(retrofit, entityId, entityType);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            333 -> {
                //fetch restraunts from entity id and entity type
                data?.let {
                    val entityId: Int = it.getIntExtra(ENTITY_ID, 0)
                    val entityType: String? = it.getStringExtra(ENTITY_TYPE)
                    if (entityId > 0) fetchRestaurantsByEntityId(entityId, entityType ?: "")
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (isPermissionGranted(this)) {
                    locationTracker.getLastLocation();
                }
            }
        }
    }

    override fun onResume() {
        super.onResume();
        if (isPermissionGranted(this)) locationTracker.getLastLocation()
    }

    override fun hideLoading() {
        runOnUiThread {
            progressBar.visibility = View.GONE
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }

    }

    override fun showLoading() {
        runOnUiThread {
            progressBar.visibility = View.VISIBLE
            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        }
    }

    override fun onLoad(data: Any) {
        runOnUiThread {
            when (data) {
                is RestaurantResponseDto -> {
                    //load restaurants
                    restaurantAdapter.addAll(data.restaurants as List<RestaurantsDto>?)
                }
            }
        }
    }

    override fun onError(error: Any) {
        when (error) {
            is Throwable -> {
                showShortToast(getString(R.string.somethingWentWrong))
                Log.d(TAG, error.printStackTrace().toString())
            }
        }
    }

    override fun onNetworkAvailable() {

    }

    override fun onNetworkLost() {
        showShortToast(getString(R.string.no_internet_text))
    }
}
