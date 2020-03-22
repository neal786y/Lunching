package com.neal786y.mvparchitecture.main.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.neal786y.mvparchitecture.R
import com.neal786y.mvparchitecture.base.BaseActivity
import com.neal786y.mvparchitecture.base.component
import com.neal786y.mvparchitecture.main.presenter.DetailWebPresenter
import com.neal786y.mvparchitecture.main.view.DetailWebView
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailWebActivity : BaseActivity<DetailWebView, DetailWebPresenter>(), DetailWebView {

    @Inject
    lateinit var detailWebPresenter: DetailWebPresenter

    override fun getLayout(): Int = R.layout.activity_detail

    override fun initPresenter(): DetailWebPresenter = detailWebPresenter

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)

        val detailUrl = intent?.extras?.getString(getString(R.string.detailUrlKey))
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = CustomWebViewClient()
        showLoading()
        detailUrl?.let {
            webView.loadUrl(detailUrl)
        }
    }

    inner class CustomWebViewClient : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            hideLoading()
        }

        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
            hideLoading()
        }
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }
    override fun onLoad(data: Any) {}

    override fun onError(error: Any) {}

    override fun onNetworkAvailable() {}

    override fun onNetworkLost() {}
}