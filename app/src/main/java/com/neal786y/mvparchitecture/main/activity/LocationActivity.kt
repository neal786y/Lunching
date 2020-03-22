package com.neal786y.mvparchitecture.main.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.neal786y.mvparchitecture.R
import com.neal786y.mvparchitecture.base.BaseActivity
import com.neal786y.mvparchitecture.base.Constants.Companion.ENTITY_ID
import com.neal786y.mvparchitecture.base.Constants.Companion.ENTITY_TYPE
import com.neal786y.mvparchitecture.base.Constants.Companion.LOCATION_REQUEST_CODE
import com.neal786y.mvparchitecture.base.Constants.Companion.LOCATION_SEARCH_DELAY
import com.neal786y.mvparchitecture.base.Constants.Companion.MIN_SEARCH_CHARACTER
import com.neal786y.mvparchitecture.base.component
import com.neal786y.mvparchitecture.base.showShortToast
import com.neal786y.mvparchitecture.main.adaper.LocationAdapter
import com.neal786y.mvparchitecture.main.listeners.OnLocationItemClickListener
import com.neal786y.mvparchitecture.main.pojo.zomato.search_locations.response.LocationResponseDto
import com.neal786y.mvparchitecture.main.pojo.zomato.search_locations.response.LocationSuggestionsDto
import com.neal786y.mvparchitecture.main.presenter.LocationPresenter
import com.neal786y.mvparchitecture.main.view.LocationView
import kotlinx.android.synthetic.main.activity_location.*
import retrofit2.Retrofit
import java.util.*
import javax.inject.Inject


class LocationActivity : BaseActivity<LocationView, LocationPresenter>(), LocationView {

    val TAG = this::class.java.simpleName

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var locationPresenter: LocationPresenter

    @Inject
    lateinit var locationAdapter: LocationAdapter


    override fun getLayout(): Int = R.layout.activity_location

    override fun initPresenter(): LocationPresenter = locationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)

        recyclerViewLocations.layoutManager = LinearLayoutManager(this)
        recyclerViewLocations.adapter = locationAdapter
        recyclerViewLocations.setEmptyView(textViewEmptyMessage)

        locationAdapter.mOnLocationItemClickListener = object : OnLocationItemClickListener{
            override fun onLocationItemClick(locationSuggestionDto: LocationSuggestionsDto) {
                val intent = Intent()
                locationSuggestionDto.entityId?.let { intent.putExtra(ENTITY_ID, it) }
                intent.putExtra(ENTITY_TYPE, locationSuggestionDto.entityType)
                setResult(LOCATION_REQUEST_CODE, intent)
                finish()
            }
        }

        edittextSearch.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            private var timer: Timer = Timer()

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(count < MIN_SEARCH_CHARACTER) return
                timer.cancel()
                timer = Timer()
                timer.schedule(
                    object : TimerTask() {
                        override fun run() {
                            presenter?.getLocation(retrofit, s.toString())
                        }
                    },
                    LOCATION_SEARCH_DELAY
                )
            }
        })
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
            when(data) {
                is LocationResponseDto -> {
                    locationAdapter.addAll(data.locationSuggestions as List<LocationSuggestionsDto>?)
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
